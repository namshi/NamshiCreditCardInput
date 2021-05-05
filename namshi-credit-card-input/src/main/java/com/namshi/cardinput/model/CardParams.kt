package com.namshi.cardinput.model

import kotlinx.parcelize.Parcelize

@Parcelize
data class CardParams internal constructor(
    private val loggingTokens: Set<String> = emptySet(),

    /**
     * Required
     *
     * The card number, as a string without any separators.
     */
    internal var number: String,

    /**
     * Required
     *
     * Two-digit number representing the card's expiration month.
     */
    internal var expMonth: Int,

    /**
     * Required
     *
     * Two- or four-digit number representing the card's expiration year.
     */
    internal var expYear: Int,

    /**
     * Usually required
     *
     * Card security code. Highly recommended to always include this value, but it's required only
     * for accounts based in European countries.
     */
    internal var cvc: String? = null,

    /**
     * Optional
     *
     * Cardholder's full name.
     */
    var name: String? = null,

    /**
     * Optional - Custom Connect Only
     *
     * Required in order to add the card to an account; in all other cases, this parameter is
     * not used. When added to an account, the card (which must be a debit card) can be used
     * as a transfer destination for funds in this currency. Currently, the only supported
     * currency for debit card payouts is `usd`.
     */
    var currency: String? = null,

    /**
     * Optional
     *
     * A set of key-value pairs that you can attach to a card object. This can be useful for
     * storing additional information about the card in a structured format.
     */
    var metadata: Map<String, String>? = null
) : TokenParams() {

    @JvmOverloads
    internal constructor(
        /**
         * Required
         *
         * The card number, as a string without any separators.
         */
        number: String,

        /**
         * Required
         *
         * Two-digit number representing the card's expiration month.
         */
        expMonth: Int,

        /**
         * Required
         *
         * Two- or four-digit number representing the card's expiration year.
         */
        expYear: Int,

        /**
         * Usually required
         *
         * Card security code. Highly recommended to always include this value, but it's required only
         * for accounts based in European countries.
         */
        cvc: String? = null,

        /**
         * Optional
         *
         * Cardholder's full name.
         */
        name: String? = null,

        /**
         * Optional - Custom Connect Only
         *
         * Required in order to add the card to an account; in all other cases, this parameter is
         * not used. When added to an account, the card (which must be a debit card) can be used
         * as a transfer destination for funds in this currency. Currently, the only supported
         * currency for debit card payouts is `usd`.
         */
        currency: String? = null,

        /**
         * Optional
         *
         * A set of key-value pairs that you can attach to a card object. This can be useful for
         * storing additional information about the card in a structured format.
         */
        metadata: Map<String, String>? = null
    ) : this(
        loggingTokens = emptySet(),
        number = number,
        expMonth = expMonth,
        expYear = expYear,
        cvc = cvc,
        name = name,
        currency = currency,
        metadata = metadata
    )

    override val typeDataParams: Map<String, Any>
        get() = listOf(
            PARAM_NUMBER to number,
            PARAM_EXP_MONTH to expMonth,
            PARAM_EXP_YEAR to expYear,
            PARAM_CVC to cvc,
            PARAM_NAME to name,
            PARAM_CURRENCY to currency,
            PARAM_METADATA to metadata
        ).fold(emptyMap()) { acc, (key, value) ->
            acc.plus(
                value?.let { mapOf(key to it) }.orEmpty()
            )
        }

    private companion object {
        private const val PARAM_NUMBER = "number"
        private const val PARAM_EXP_MONTH = "exp_month"
        private const val PARAM_EXP_YEAR = "exp_year"
        private const val PARAM_CVC = "cvc"
        private const val PARAM_NAME = "name"
        private const val PARAM_CURRENCY = "currency"
        private const val PARAM_METADATA = "metadata"
    }
}
