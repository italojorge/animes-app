package br.com.animes.feature.home.data.remote.model

import com.google.gson.annotations.SerializedName

data class AnimeListResponse(
    @SerializedName("top")
    val animeListResponse: List<AnimeResponse>
)
