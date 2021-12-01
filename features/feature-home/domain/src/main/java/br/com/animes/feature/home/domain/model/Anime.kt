package br.com.animes.feature.home.domain.model

data class Anime(
    val id: Int,
    val rank: Long,
    val title: String,
    val image_url: String,
    val type: String,
    val episodes: Int,
    val start_date: String,
    val end_date: String,
    val members: Long,
)