package br.com.animes.feature.home

import android.content.Context
import androidx.annotation.StringRes

enum class FilterTopAnimesBindingEnum(@StringRes val stringRes: Int) {
    AIRING(R.string.airing), UPCOMING(R.string.upcoming), TV(R.string.tv), MOVIE(R.string.movie),
    OVA(R.string.ova), SPECIAL(R.string.special);

    companion object {
        fun valueOfByStringRes(context: Context, text: String): FilterTopAnimesBindingEnum {
            return values().find {
                text == context.getString(it.stringRes)
            } ?: throw IllegalStateException()
        }
    }
}
