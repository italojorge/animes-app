package br.com.animes.feature.auth.data.remote.service

import br.com.animes.feature.auth.data.remote.model.LoginResponse
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginWebService {
    @POST("https://app.fakejson.com/q/2SkehilJ?token=Ld9SwOFUDuYzrc_KkEF5fg")
    suspend fun doLogin(
        @Header("Authorization") basicAuth: String
    ): LoginResponse
}