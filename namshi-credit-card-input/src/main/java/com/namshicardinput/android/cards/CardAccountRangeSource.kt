package com.namshicardinput.android.cards

import com.namshicardinput.android.model.AccountRange
import kotlinx.coroutines.flow.Flow

internal interface CardAccountRangeSource {
    suspend fun getAccountRange(
        cardNumber: CardNumber.Unvalidated
    ): AccountRange?

    val loading: Flow<Boolean>
}
