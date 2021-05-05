package com.namshi.cardinput.view

import android.graphics.Color
import androidx.annotation.ColorInt

internal object ColorUtils {

    /**
     * A crude mechanism by which we check whether or not a color is "dark".
     * This is subject to much interpretation, but we attempt to follow traditional
     * design standards.
     *
     * Formula comes from W3C standards and conventional theory about how to calculate the
     * "brightness" of a color, often thought of as how far along the spectrum from white to black
     * the gray-scale version would be.
     *
     * See [W3C's Techniques For Accessibility Evaluation And Repair Tools](https://www.w3.org/TR/AERT#color-contrast)
     * and [RGB colour space](http://paulbourke.net/miscellaneous/colourspace/) for further reading.
     *
     * @param color an integer representation of a color
     * @return `true` if the color is "dark", else `false`
     */
    fun isColorDark(@ColorInt color: Int): Boolean {
        val luminescence = 0.299 * Color.red(color) +
          0.587 * Color.green(color) +
          0.114 * Color.blue(color)

        // Because the colors are all hex integers.
        val luminescencePercentage = luminescence / 255
        return luminescencePercentage <= 0.5
    }
}
