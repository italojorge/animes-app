package br.com.animes.feature.home.data.remote.model

import com.google.gson.annotations.SerializedName

data class QueryAnimeListResponse(
    @SerializedName("results")
    val animeListResponse: List<AnimeResponse>
)