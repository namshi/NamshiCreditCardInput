package com.namshicardinput.android.cards

import com.namshicardinput.android.model.AccountRange

internal interface StaticCardAccountRanges {
    fun match(
        cardNumber: CardNumber.Unvalidated
    ): AccountRange?
}
