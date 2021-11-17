package br.com.animes.base.feature.utils.extensions

import android.animation.ObjectAnimator
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

fun View.setGone() {
    visibility = View.GONE
}

fun View.setInvisible() {
    visibility = View.INVISIBLE
}

fun View.setVisible() {
    visibility = View.VISIBLE
}

fun ProgressBar.animatedProgressTo(fromValue: Number, toValue: Number) {
    progress = fromValue.toInt()
    animatedProgressTo(toValue)
}

fun ProgressBar.animatedProgressTo(toValue: Number) {
    ObjectAnimator.ofInt(this, "progress", toValue.toInt())
        .setDuration(300)
        .start()
}


fun withViews(vararg views: View, action: (View) -> Any) {
    views.forEach {
        action(it)
    }
}

fun TextView.addDrawableStart(resId: Int) {
    setCompoundDrawablesWithIntrinsicBounds(
        ContextCompat.getDrawable(context, resId), null, null, null
    )
}