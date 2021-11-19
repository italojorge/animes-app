/*
 * Copyright 2014 Leonardo Rossetto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.animes.core.utils.watchers

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import br.com.animes.base_feature.R
import java.lang.ref.WeakReference
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

/**
 * Monetary mask formats the input to a currency format by the given locale
 *
 * @author Leonardo Rossetto
 */
class MoneyMask(editText: EditText) : TextWatcher {

    private val mText: WeakReference<EditText> = WeakReference(editText)
    private val mFormatter: NumberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    private var mIsUpdating: Boolean = false

    override fun afterTextChanged(s: Editable) {}

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        mText.get()?.setSelection(mText.get()?.text?.length ?: 0)
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        var aux = s
        if (mIsUpdating) {
            mIsUpdating = false
            return
        }
        mIsUpdating = true
        val str = unmask(aux.toString())

        try {
            val parsed = java.lang.Double.parseDouble(str)
            if (parsed == 0.toDouble()) {
                mText.get()?.text = null
                return
            }
            aux = mFormatter.format(parsed / 100)
            if (mText.get()?.text?.toString()?.contentEquals(aux) == false) {
                val v = String.format(mText.get()?.context?.getString(R.string.default_currency_formatter) ?: "", aux)
                mText.get()?.setText(v)
            }
        } catch (e: NumberFormatException) {
        }

        mText.get()?.setSelection(mText.get()?.text?.length ?: 0)
    }

    companion object {

        fun getBRFormatter(currencyView: EditText): MoneyMask {
            val mask = MoneyMask(currencyView)
            val formatter = mask.mFormatter as DecimalFormat
            val symbols = formatter.decimalFormatSymbols
            symbols.currencySymbol = ""
            formatter.decimalFormatSymbols = symbols
            return mask
        }

        fun unmask(masked: String?) = masked?.replace("[^\\d]".toRegex(), "") ?: ""

        fun doubleValue(masked: String): Double = try {
            java.lang.Double.parseDouble(unmask(masked)) / 100
        } catch (ignore: NumberFormatException) {
            0.0
        }
    }
}
