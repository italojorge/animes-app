package br.com.animes.feature.home.data.remote.model

import com.google.gson.annotations.SerializedName

data class AnimeResponse(
    @SerializedName("mal_id")
    val id: Long?,
    val rank: Long?,
    val title: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    val type: String?,
    val episodes: Long?,
    @SerializedName("start_date")
    val startDate: String?,
    @SerializedName("end_date")
    val endDate: String?,
    val members: Long?,
    val score: Double?
)