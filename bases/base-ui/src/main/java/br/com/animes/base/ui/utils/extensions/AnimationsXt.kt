package br.com.animes.base.feature.utils.extensions

import android.view.View

fun View.hideWithFade(duration: Int = 200, startDelay: Int = 0, doAfter: () -> Unit = {}) {
    setVisible()
    alpha = 1f
    animate().apply {
        alpha(0f)
        this.startDelay = startDelay.toLong()
        this.duration = duration.toLong()
        withEndAction {
            doAfter()
            setInvisible()
        }
        start()
    }
}

fun View.showWithFade(duration: Int = 200, startDelay: Int = 0, doAfter: () -> Unit = {}) {
    alpha = 0f
    setVisible()
    animate().apply {
        alpha(1f)
        this.startDelay = startDelay.toLong()
        this.duration = duration.toLong()
        withEndAction {
            doAfter()
        }
        start()
    }
}