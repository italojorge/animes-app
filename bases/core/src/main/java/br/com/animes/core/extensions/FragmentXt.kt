package br.com.animes.core.extensions

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar

fun Fragment.hideKeyboard() {
    val activity = requireActivity()
    val view = activity.window.currentFocus
    val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    view?.let {
        imm?.let {
            if (it.isAcceptingText) {
                it.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }
}

fun Fragment.setupToolbar(toolbar: MaterialToolbar){
    (activity as? AppCompatActivity)?.apply {
        setSupportActionBar(toolbar)
    }
}