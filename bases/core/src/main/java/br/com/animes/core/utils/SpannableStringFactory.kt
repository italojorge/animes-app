@file:Suppress("unused")

package br.com.animes.core.utils

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.*
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat

object SpannableStringFactory {

    fun addMessage(message: String): SpannableString {
        return SpannableString(message)
    }

}

fun TextView.setSpannable(f: (SpannableString) -> SpannableString) {
    text = f(SpannableString(text.toString()))
}

fun SpannableString.addEndDrawable(
    context: Context,
    drawableRes: Int
): SpannableString {

    val imageSpan = ImageSpan(context, drawableRes)
    setSpan(imageSpan, length - 1, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    return this
}

fun SpannableString.addDrawableWithNoBoundsAt(
    context: Context,
    start: Int = 0,
    end: Int = 0,
    drawableRes: Int
): SpannableString {
    val drawable = ContextCompat.getDrawable(context, drawableRes) ?: throw RuntimeException()

    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    val span = ImageSpan(drawable)
    this.setSpan(span, start, end, 0)

    return this
}

fun SpannableString.changeColorAt(
    start: Int,
    end: Int,
    color: Int
): SpannableString {
    try {
        setSpan(
            ForegroundColorSpan(color),
            start, end,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
    } catch (e: RuntimeException) {
        e.printStackTrace()
    }
    return this
}

fun SpannableString.changeFontAt(
    start: Int,
    end: Int,
    styleSpan: StyleSpan
): SpannableString {
    this.setSpan(
        styleSpan,
        start, end,
        Spannable.SPAN_INCLUSIVE_EXCLUSIVE
    )
    return this
}

fun SpannableString.clickPartOfTextAt(
    start: Int,
    end: Int,
    isUnderline: Boolean = true,
    onClick: () -> Unit
): SpannableString {
    val clickableSpan = object : ClickableSpan() {
        override fun onClick(textView: View) {
            textView.invalidate()
            onClick()
        }

        override fun updateDrawState(ds: TextPaint) {
            ds.isUnderlineText = isUnderline
        }

    }
    this.setSpan(
        clickableSpan,
        start,
        end,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return this
}

fun SpannableString.changeFontSizeAt(
    start: Int,
    end: Int,
    fontSize: Float
): SpannableString {
    this.setSpan(
        RelativeSizeSpan(fontSize),
        start, end,
        Spannable.SPAN_INCLUSIVE_EXCLUSIVE
    )

    return this
}