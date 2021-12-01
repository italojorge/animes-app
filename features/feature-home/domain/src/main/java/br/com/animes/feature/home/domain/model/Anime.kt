package br.com.animes.feature.home.domain.model

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
)