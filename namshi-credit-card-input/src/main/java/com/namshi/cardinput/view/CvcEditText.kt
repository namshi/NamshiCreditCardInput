package com.namshi.cardinput.view

import android.content.Context
import android.os.Build
import android.text.InputFilter
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.util.AttributeSet
import android.view.View
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputLayout
import com.namshi.cardinput.R
import com.namshi.cardinput.cards.Cvc
import com.namshi.cardinput.model.CardBrand

/**
 * A [NamshiEditText] for CVC input.
 */
class CvcEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = androidx.appcompat.R.attr.editTextStyle
) : NamshiEditText(context, attrs, defStyleAttr) {

    private val unvalidatedCvc: Cvc.Unvalidated
        get() {
            return Cvc.Unvalidated(fieldText)
        }

    internal val cvc: Cvc.Validated?
        get() {
            return unvalidatedCvc.validate(cardBrand.maxCvcLength)
        }

    private var cardBrand: CardBrand = CardBrand.Unknown

    // invoked when a valid CVC has been entered
    @JvmSynthetic
    internal var completionCallback: () -> Unit = {}

    init {
        setErrorMessage(resources.getString(R.string.invalid_cvc))
        setHint(R.string.cvc_number_hint)
        maxLines = 1
        filters = createFilters(CardBrand.Unknown)

        inputType = InputType.TYPE_NUMBER_VARIATION_PASSWORD
        keyListener = DigitsKeyListener.getInstance(false, true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setAutofillHints(View.AUTOFILL_HINT_CREDIT_CARD_SECURITY_CODE)
        }

        doAfterTextChanged {
            shouldShowError = false
            if (cardBrand.isMaxCvc(unvalidatedCvc.normalized)) {
                completionCallback()
            }
        }

    }

    override val accessibilityText: String?
        get() {
            return resources.getString(R.string.acc_label_cvc_node, text)
        }

    /**
     * @param cardBrand the [CardBrand] used to update the view
     * @param customHintText optional user-specified hint text
     * @param textInputLayout if specified, hint text will be set on this [TextInputLayout]
     * instead of directly on the [CvcEditText]
     */
    @JvmSynthetic
    internal fun updateBrand(
        cardBrand: CardBrand,
        customHintText: String? = null,
        textInputLayout: TextInputLayout? = null
    ) {
        this.cardBrand = cardBrand
        filters = createFilters(cardBrand)

        val hintText = customHintText
            ?: if (cardBrand == CardBrand.AmericanExpress) {
                resources.getString(R.string.cvc_amex_hint)
            } else {
                resources.getString(R.string.cvc_number_hint)
            }

        if (textInputLayout != null) {
            textInputLayout.hint = hintText
        } else {
            this.hint = hintText
        }
    }

    private fun createFilters(cardBrand: CardBrand): Array<InputFilter> {
        return arrayOf(InputFilter.LengthFilter(cardBrand.maxCvcLength))
    }
}
