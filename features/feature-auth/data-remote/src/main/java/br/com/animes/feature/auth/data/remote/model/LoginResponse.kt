package br.com.animes.feature.auth.data.remote.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    val bearerToken: String?
)
