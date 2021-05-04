package com.namshi.cardinput.cards

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class Bin internal constructor(
    internal val value: String
): Parcelable {
    override fun toString() = value

    companion object {
        fun create(cardNumber: String): Bin? {
            return cardNumber
                .take(BIN_LENGTH)
                .takeIf {
                    it.length == BIN_LENGTH
                }?.let {
                    Bin(it)
                }
        }

        private const val BIN_LENGTH = 6
    }
}
