package br.com.animes.base.feature.utils.extensions

import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputLayout

fun EditText.doOnSubmit(submit: (query: String) -> Unit) {
    setOnEditorActionListener { textView, _, _ ->
        submit(textView.text.toString())
        true
    }
}

fun TextInputLayout.cleanErrorTextAfterTextChanged() {
    editText?.doAfterTextChanged {
        this.error = ""
    }
}

