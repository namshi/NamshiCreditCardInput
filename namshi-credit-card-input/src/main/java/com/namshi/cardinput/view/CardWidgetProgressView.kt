package com.namshi.cardinput.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import androidx.core.view.updateLayoutParams
import com.namshi.cardinput.R
import com.namshi.cardinput.databinding.CardWidgetProgressViewBinding

internal class CardWidgetProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val fadeIn = AnimationUtils.loadAnimation(
        context,
        R.anim.card_widget_progress_fade_in
    ).also {
        it.setAnimationListener(
            object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {
                    visibility = View.VISIBLE
                }

                override fun onAnimationEnd(p0: Animation?) {
                }

                override fun onAnimationRepeat(p0: Animation?) {
                }
            }
        )
    }

    private val fadeOut = AnimationUtils.loadAnimation(
        context,
        R.anim.card_widget_progress_fade_out
    ).also {
        it.setAnimationListener(
            object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {
                    visibility = View.VISIBLE
                }

                override fun onAnimationEnd(p0: Animation?) {
                    visibility = View.INVISIBLE
                }

                override fun onAnimationRepeat(p0: Animation?) {
                }
            }
        )
    }

    init {
        CardWidgetProgressViewBinding.inflate(
            LayoutInflater.from(context),
            this
        )

        setBackgroundResource(R.drawable.card_progress_background)
        clipToOutline = true
        visibility = View.INVISIBLE
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        val size = context.resources.getDimensionPixelSize(R.dimen.card_widget_progress_size)
        updateLayoutParams {
            width = size
            height = size
        }
    }

    fun show() {
        startAnimation(fadeIn)
    }

    fun hide() {
        startAnimation(fadeOut)
    }
}
