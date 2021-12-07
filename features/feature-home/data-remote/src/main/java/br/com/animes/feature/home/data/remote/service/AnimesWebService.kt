package br.com.animes.feature.home.data.remote.service

import br.com.animes.feature.home.data.remote.model.FilterAnimeListResponse
import br.com.animes.feature.home.data.remote.model.QueryAnimeListResponse
import br.com.animes.feature.home.data.remote.model.details.AnimeDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimesWebService {
    @GET("top/anime/{page}/{filter}")
    suspend fun getAnimeListByFilter(
        @Path("page") page: Int,
        @Path("filter") filter: String,
    ): FilterAnimeListResponse

    @GET("search/anime")
    suspend fun getAnimeListByQuery(
        @Query("q") query: String,
        @Query("page") page: Int,
    ): QueryAnimeListResponse

    @GET("anime/{id}")
    suspend fun getAnimeDetails(
        @Path("id") id: Long
    ): AnimeDetailsResponse
}