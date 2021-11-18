package br.com.animes.base.feature.utils.extensions

import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.FontRes
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.tabs.TabLayout

fun TabLayout.addTab(vararg title: String): TabLayout {
    title.forEach { addTab(newTab().setText(it)) }
    return this
}

fun TabLayout.setTextColorAt(index: Int, @ColorRes colorId: Int): TabLayout {
    getTextViewAt(index)?.setTextColor(getColor(context, colorId))
    return this
}

fun TabLayout.getTextViewAt(index: Int): TextView? {
    return (((getChildAt(0) as? LinearLayout)?.getChildAt(index) as? LinearLayout)
        ?.getChildAt(1) as? TextView)
}

fun TabLayout.changeFontWhenSelected(@FontRes normalFont: Int, @FontRes selectedFont: Int) {
    addOnTabSelectedListener(
        object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab) {
                getTextViewAt(tab.position)?.typeface = ResourcesCompat.getFont(context, normalFont)
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                getTextViewAt(tab.position)?.typeface =
                    ResourcesCompat.getFont(context, selectedFont)
            }
        }
    )
}

