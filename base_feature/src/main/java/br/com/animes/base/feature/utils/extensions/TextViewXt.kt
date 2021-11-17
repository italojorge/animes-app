package br.com.animes.base.feature.utils.extensions

import android.graphics.Color
import android.text.method.LinkMovementMethod
import android.widget.TextView
import br.com.animes.base.feature.utils.clickPartOfTextAt
import br.com.animes.base.feature.utils.setSpannable

fun TextView.addOnPartClickListener(
    substring: String,
    isUnderline: Boolean = true,
    onClick: () -> Unit
) {
    val start = text.indexOf(substring)

    setSpannable {
        it.clickPartOfTextAt(start, start + substring.length, isUnderline) {
            onClick()
        }
        it
    }

    movementMethod = LinkMovementMethod.getInstance()

    setOnClickListener {
        invalidate()
    }
}

fun TextView.addOnPartClickListener(vararg onClick: TextClick) {

    setSpannable {
        onClick.forEach { e ->
            val start = text.indexOf(e.substring)
            it.clickPartOfTextAt(start, start + e.substring.length, e.isUnderline) {
                e.onClick()
            }
        }
        it
    }

    highlightColor = Color.TRANSPARENT
    movementMethod = LinkMovementMethod.getInstance()

    setOnClickListener {
        invalidate()
    }
}

data class TextClick(
    val substring: String,
    val isUnderline: Boolean = false,
    val onClick: () -> Unit
)

