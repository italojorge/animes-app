package br.com.animes.data.remote.service

import br.com.animes.data.remote.model.LoginResponse
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginWebService {
    @POST("v1/auth/login")
    suspend fun doLogin(@Header("Authorization") basicAuth: String): LoginResponse
}