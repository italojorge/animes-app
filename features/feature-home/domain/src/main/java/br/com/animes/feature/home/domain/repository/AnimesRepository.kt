package br.com.animes.feature.home.domain.repository

import androidx.paging.PagingData
import br.com.animes.feature.home.domain.model.Anime
import kotlinx.coroutines.flow.Flow

interface AnimesRepository {
    fun getAnimesByQuery(query: String): Flow<PagingData<Anime>>
    fun getAnimesByFilter(filterName: String): Flow<PagingData<Anime>>
}