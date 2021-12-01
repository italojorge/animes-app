package br.com.animes.feature.home.data.remote.service

import br.com.animes.feature.home.data.remote.model.AnimeListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimesWebService {
    @GET("top/anime/{page}/{filter}")
    suspend fun getAnimeListByFilter(
        @Path("page") page: Int,
        @Path("filter") filter: String,
    ): AnimeListResponse
}