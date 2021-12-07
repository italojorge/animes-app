package br.com.animes.feature.home.domain.model

data class AnimeDetails(
    val id: Long,
    val title: String,
    val score: Double,
    val imageUrl: String,
    val synopsis: String,
    val type: String,
    val episodes: Long?,
    val date: String?,
    val members: Long,
    val status: String?,
    val rank: Long?,
    val source: String?,
    val duration: String?
)