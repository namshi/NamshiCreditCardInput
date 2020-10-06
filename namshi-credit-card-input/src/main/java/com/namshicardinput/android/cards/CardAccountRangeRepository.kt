package com.namshicardinput.android.cards

import com.namshicardinput.android.model.AccountRange
import kotlinx.coroutines.flow.Flow

internal interface CardAccountRangeRepository {
    suspend fun getAccountRange(
        cardNumber: CardNumber.Unvalidated
    ): AccountRange?

    /**
     * Flow that represents whether any of the [CardAccountRangeSource] instances are loading.
     */
    val loading: Flow<Boolean>

    interface Factory {
        fun create(): CardAccountRangeRepository
    }
}
