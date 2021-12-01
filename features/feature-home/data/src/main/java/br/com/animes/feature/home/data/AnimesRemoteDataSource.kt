package br.com.animes.feature.home.data

import br.com.animes.domain.utils.Result
import br.com.animes.feature.home.domain.model.Anime

interface AnimesRemoteDataSource {
    suspend fun requestAnimeList(page: Int): Result<List<Anime>>
}