package br.com.animes.feature.home.data.remote.model.details

import com.google.gson.annotations.SerializedName

data class AnimeDetailsDateResponse(
    @SerializedName("string")
    val date: String?,
)