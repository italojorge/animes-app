package br.com.animes.feature.home.data

import br.com.animes.domain.utils.Result
import br.com.animes.feature.home.domain.model.Anime
import br.com.animes.feature.home.domain.model.AnimeDetails
import br.com.animes.feature.home.domain.model.FilterTopAnimesEnum

interface AnimesRemoteDataSource {
    suspend fun requestAnimeListByFilter(filter: FilterTopAnimesEnum, page: Int): Result<List<Anime>>
    suspend fun requestAnimeListByQuery(query: String, page: Int): Result<List<Anime>>
    suspend fun getAnimeDetails(id: Long): Result<AnimeDetails>
}