package com.namshi.cardinput.cards

import com.namshi.cardinput.model.AccountRange
import kotlinx.coroutines.flow.Flow

internal interface CardAccountRangeSource {
    suspend fun getAccountRange(
        cardNumber: CardNumber.Unvalidated
    ): AccountRange?

    val loading: Flow<Boolean>
}
