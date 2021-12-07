package br.com.animes.feature.home.details

import android.content.Context
import androidx.annotation.StringRes
import br.com.animes.feature.home.R
import br.com.animes.feature.home.domain.model.AnimeDetails

object AnimeInformationList {
    fun getList(context: Context, animeDetails: AnimeDetails) =
        listOf(
            getInformationFormatted(context, R.string.item_anime_details_type_label, animeDetails.type),
            getInformationFormatted(context, R.string.item_anime_details_episodes_label, animeDetails.episodes?.toString()),
            getInformationFormatted(context, R.string.item_anime_details_date_label, animeDetails.date),
            getInformationFormatted(context, R.string.item_anime_details_members_label, animeDetails.members.toString()),
            getInformationFormatted(context, R.string.item_anime_details_status_label, animeDetails.status),
            getInformationFormatted(context, R.string.item_anime_details_rank_label, animeDetails.rank?.toString()),
            getInformationFormatted(context, R.string.item_anime_details_source_label, animeDetails.source),
            getInformationFormatted(context, R.string.item_anime_details_duration_label, animeDetails.duration)
        )


    private fun getInformationFormatted(context: Context, @StringRes label: Int, value: String?) =
        context.getString(
            R.string.item_anime_details_information_format, context.getString(label),
            if (value?.isNotBlank() == true) value else WITHOUT_VALUE_SYMBOL
        )

    private const val WITHOUT_VALUE_SYMBOL = "???"
}