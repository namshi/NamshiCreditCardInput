package com.namshi.cardinput.cards

import com.namshi.cardinput.model.AccountRange

internal interface StaticCardAccountRanges {
    fun match(
        cardNumber: CardNumber.Unvalidated
    ): AccountRange?
}
