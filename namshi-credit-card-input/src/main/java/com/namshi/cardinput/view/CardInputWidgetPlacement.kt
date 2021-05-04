package com.namshi.cardinput.view

import android.view.View

/**
 * A class for tracking the placement and layout of fields in [CardInputWidget].
 */
internal data class CardInputWidgetPlacement(
    internal var totalLengthInPixels: Int = 0,

    internal var cardWidth: Int = 0,
    internal var hiddenCardWidth: Int = 0,
    internal var peekCardWidth: Int = 0,
    internal var cardDateSeparation: Int = 0,
    internal var dateWidth: Int = 0,
    internal var dateCvcSeparation: Int = 0,
    internal var cvcWidth: Int = 0,

    internal var cardTouchBufferLimit: Int = 0,
    internal var dateStartPosition: Int = 0,
    internal var dateEndTouchBufferLimit: Int = 0,
    internal var cvcStartPosition: Int = 0,
    internal var cvcEndTouchBufferLimit: Int = 0
) {
    private val cardPeekDateStartMargin: Int
        @JvmSynthetic
        get() {
            return peekCardWidth + cardDateSeparation
        }

    private val cardPeekCvcStartMargin: Int
        @JvmSynthetic
        get() {
            return cardPeekDateStartMargin + dateWidth + dateCvcSeparation
        }

    @JvmSynthetic
    internal fun getDateStartMargin(isFullCard: Boolean): Int {
        return if (isFullCard) {
            cardWidth + cardDateSeparation
        } else {
            cardPeekDateStartMargin
        }
    }

    @JvmSynthetic
    internal fun getCvcStartMargin(isFullCard: Boolean): Int {
        return if (isFullCard) {
            totalLengthInPixels
        } else {
            cardPeekCvcStartMargin
        }
    }

    @JvmSynthetic
    internal fun updateSpacing(
        isShowingFullCard: Boolean,
        frameStart: Int,
        frameWidth: Int
    ) {
        when {
            isShowingFullCard -> {
                cardDateSeparation = frameWidth - cardWidth - dateWidth
                cardTouchBufferLimit = frameStart + cardWidth + cardDateSeparation / 2
                dateStartPosition = frameStart + cardWidth + cardDateSeparation
            }
            else -> {
                this.cardDateSeparation = frameWidth / 2 - peekCardWidth - dateWidth / 2
                this.dateCvcSeparation = frameWidth - peekCardWidth - cardDateSeparation -
                    dateWidth - cvcWidth

                this.cardTouchBufferLimit = frameStart + peekCardWidth + cardDateSeparation / 2
                this.dateStartPosition = frameStart + peekCardWidth + cardDateSeparation

                this.dateEndTouchBufferLimit = dateStartPosition + dateWidth + dateCvcSeparation / 2
                this.cvcStartPosition = dateStartPosition + dateWidth + dateCvcSeparation
            }
        }
    }

    /**
     * Checks on the horizontal position of a touch event to see if
     * that event needs to be associated with one of the controls even
     * without having actually touched it. This essentially gives a larger
     * touch surface to the controls. We return `null` if the user touches
     * actually inside the widget because no interception is necessary - the touch will
     * naturally give focus to that control, and we don't want to interfere with what
     * Android will naturally do in response to that touch.
     *
     * @param touchX distance in pixels from the start of this control
     * @return a [Field] that represents the [View] to request focus, or `null`
     * if no such request is necessary.
     */
    internal fun getFocusField(
        touchX: Int,
        frameStart: Int,
        isShowingFullCard: Boolean
    ) = when {
        isShowingFullCard -> {
            // Then our view is
            // |full card||space||date|

            when {
                touchX < frameStart + cardWidth -> // Then the card edit view will already handle this touch.
                    null
                touchX < cardTouchBufferLimit -> // Then we want to act like this was a touch on the card view
                    CardInputWidget.Field.Number
                touchX < dateStartPosition -> // Then we act like this was a touch on the date editor.
                    CardInputWidget.Field.Expiry
                else -> // Then the date editor will already handle this touch.
                    null
            }
        }
        else -> {
            // Our view is
            // |peek card||space||date||space||cvc|
            when {
                touchX < frameStart + peekCardWidth -> // This was a touch on the card number editor, so we don't need to handle it.
                    null
                touchX < cardTouchBufferLimit -> // Then we need to act like the user touched the card editor
                    CardInputWidget.Field.Number
                touchX < dateStartPosition -> // Then we need to act like this was a touch on the date editor
                    CardInputWidget.Field.Expiry
                touchX < dateStartPosition + dateWidth -> // Just a regular touch on the date editor.
                    null
                touchX < dateEndTouchBufferLimit -> // We need to act like this was a touch on the date editor
                    CardInputWidget.Field.Expiry
                touchX < cvcStartPosition -> // We need to act like this was a touch on the cvc editor.
                    CardInputWidget.Field.Cvc
                else -> null
            }
        }
    }

    override fun toString(): String {
        val touchBufferData =
            """
            Touch Buffer Data:
            CardTouchBufferLimit = $cardTouchBufferLimit
            DateStartPosition = $dateStartPosition
            DateEndTouchBufferLimit = $dateEndTouchBufferLimit
            CvcStartPosition = $cvcStartPosition
            CvcEndTouchBufferLimit = $cvcEndTouchBufferLimit
            """

        val elementSizeData =
            """
            TotalLengthInPixels = $totalLengthInPixels
            CardWidth = $cardWidth
            HiddenCardWidth = $hiddenCardWidth
            PeekCardWidth = $peekCardWidth
            CardDateSeparation = $cardDateSeparation
            DateWidth = $dateWidth
            DateCvcSeparation = $dateCvcSeparation
            CvcWidth = $cvcWidth
            """

        return elementSizeData + touchBufferData
    }
}
