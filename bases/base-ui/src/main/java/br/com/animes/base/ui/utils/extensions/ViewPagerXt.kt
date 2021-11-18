package br.com.animes.base.feature.utils.extensions

import androidx.viewpager.widget.ViewPager

fun ViewPager.onPageSelected(pageSelected: (Int) -> Unit) {
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            pageSelected(position)
        }
    })
}