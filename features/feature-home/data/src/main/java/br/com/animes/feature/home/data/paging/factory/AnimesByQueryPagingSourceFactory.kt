package br.com.animes.feature.home.data.paging.factory

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.animes.feature.home.data.AnimesRemoteDataSource
import br.com.animes.feature.home.domain.model.Anime

class AnimesByQueryPagingSourceFactory(
    private val remoteDataSource: AnimesRemoteDataSource,
    private val query: String
) : PagingSource<Int, Anime>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {
        val position = params.key ?: ANIME_LIST_STARTING_PAGE_INDEX
        val result = remoteDataSource.requestAnimeListByQuery(query, position)
        return if (result.isSuccess) {
            val animeList = result.getOrNull()
            val nextKey = if (animeList.isNullOrEmpty()) {
                null
            } else {
                position + PAGING_INCREMENT_VALUE
            }
            LoadResult.Page(
                data = animeList.orEmpty(),
                prevKey = if (position == ANIME_LIST_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } else {
            return LoadResult.Error(result.getErrorOrNull() ?: Exception())
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Anime>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(PAGING_INCREMENT_VALUE)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(PAGING_INCREMENT_VALUE)
        }
    }

    companion object {
        private const val ANIME_LIST_STARTING_PAGE_INDEX = 1
        private const val PAGING_INCREMENT_VALUE = 1
    }
}
