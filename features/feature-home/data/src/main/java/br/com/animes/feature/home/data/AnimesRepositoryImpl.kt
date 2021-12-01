package br.com.animes.feature.home.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.animes.feature.home.domain.ANIMES_PAGE_SIZE
import br.com.animes.feature.home.domain.model.Anime
import br.com.animes.feature.home.domain.repository.AnimesRepository
import kotlinx.coroutines.flow.Flow

class AnimesRepositoryImpl(private val remoteDataSource: AnimesRemoteDataSource) : AnimesRepository {
    override fun getAnimesByQuery(query: String?): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(
                pageSize = ANIMES_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { AnimesPagingSourceFactory(remoteDataSource) }
        ).flow
    }
}