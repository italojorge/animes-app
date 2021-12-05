package br.com.animes.feature.home.data.remote.model

import com.google.gson.annotations.SerializedName

data class FilterAnimeListResponse(
    @SerializedName("top")
    val animeListResponse: List<AnimeResponse>
)
