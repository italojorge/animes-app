package br.com.animes.feature.home.data

import br.com.animes.domain.utils.Result
import br.com.animes.feature.home.domain.model.Anime

interface AnimesRemoteDataSource {
    suspend fun requestAnimeListByFilter(filterName: String, page: Int): Result<List<Anime>>
    suspend fun requestAnimeListByQuery(query: String, page: Int): Result<List<Anime>>
}