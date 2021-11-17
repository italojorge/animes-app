package br.com.animes.data.remote.model

import com.google.gson.annotations.SerializedName

data class ChangePasswordRequest(
    @SerializedName("password")
    val password: String,
    @SerializedName("password_confirm")
    val passwordConfirm: String = password
)
