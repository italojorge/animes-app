package br.com.animes.data.remote.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    val bearerToken: String?
)
