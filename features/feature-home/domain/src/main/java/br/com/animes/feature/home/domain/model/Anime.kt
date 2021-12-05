package br.com.animes.feature.home.domain.model

import br.com.animes.domain.utils.formatToAppDateOrNull

data class Anime(
    val id: Long,
    val rank: Long,
    val title: String,
    val imageUrl: String,
    val type: String,
    val episodes: Long?,
    val startDate: String?,
    val endDate: String?,
    val members: Long,
    val score: Double
) {
    fun getFormattedDate(): String {
        val startDateFormatted = startDate?.formatToAppDateOrNull() ?: startDate
        val endDateFormatted = endDate?.formatToAppDateOrNull() ?: endDate

        return when {
            startDateFormatted != null && endDateFormatted != null -> {
                "$startDateFormatted - $endDateFormatted"
            }
            startDateFormatted != null -> {
                startDateFormatted
            }
            endDateFormatted != null -> {
                endDateFormatted
            }
            else -> ""
        }
    }
}