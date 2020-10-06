package com.namshicardinput.android.view

import android.text.TextWatcher
import androidx.annotation.IntRange
import com.namshicardinput.android.model.CardParams


internal interface CardWidget {

    /**
     * A [CardParams] representing the card details and postal code if all fields are valid;
     * otherwise `null`
     */
    val cardParams: CardParams?

    fun setCardValidCallback(callback: CardValidCallback?)

    fun setCardInputListener(listener: CardInputListener?)

    /**
     * Set a `TextWatcher` to receive card number changes.
     */
    fun setCardNumberTextWatcher(cardNumberTextWatcher: TextWatcher?)

    /**
     * Set a `TextWatcher` to receive expiration date changes.
     */
    fun setExpiryDateTextWatcher(expiryDateTextWatcher: TextWatcher?)

    /**
     * Set a `TextWatcher` to receive CVC value changes.
     */
    fun setCvcNumberTextWatcher(cvcNumberTextWatcher: TextWatcher?)

    fun setCardHint(cardHint: String)

    fun clear()

    fun setCardNumber(cardNumber: String?)

    fun setExpiryDate(
        @IntRange(from = 1, to = 12) month: Int,
        @IntRange(from = 0, to = 9999) year: Int
    )

    fun setCvcCode(cvcCode: String?)
}
