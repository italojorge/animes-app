package br.com.animes.feature.home.domain.repository

import androidx.paging.PagingData
import br.com.animes.domain.utils.Result
import br.com.animes.feature.home.domain.model.Anime
import br.com.animes.feature.home.domain.model.AnimeDetails
import br.com.animes.feature.home.domain.model.FilterTopAnimesEnum
import kotlinx.coroutines.flow.Flow

interface AnimesRepository {
    fun getAnimesByQuery(query: String): Flow<PagingData<Anime>>
    fun getAnimesByFilter(filter: FilterTopAnimesEnum): Flow<PagingData<Anime>>
    suspend fun getAnimeDetails(id: Long): Result<AnimeDetails>
}