package br.com.animes.feature.home.data.remote.model.details

import com.google.gson.annotations.SerializedName

data class AnimeDetailsResponse(
    @SerializedName("mal_id")
    val id: Long?,
    val rank: Long?,
    val title: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    val type: String?,
    val episodes: Long?,
    @SerializedName("aired")
    val dateResponse: AnimeDetailsDateResponse?,
    val members: Long?,
    val score: Double?,
    val synopsis: String?,
    val status: String?,
    val source: String?,
    val duration: String?
)