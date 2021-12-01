package br.com.animes.feature.home.data.remote.datasource

import br.com.animes.base.data.remote.utils.RetrofitWrapper.retrofitWrapper
import br.com.animes.domain.utils.Result
import br.com.animes.feature.home.data.AnimesRemoteDataSource
import br.com.animes.feature.home.data.remote.mapper.toDomain
import br.com.animes.feature.home.data.remote.service.AnimesWebService
import br.com.animes.feature.home.domain.model.Anime

class AnimesRemoteDataSourceImpl(
    private val animesWebService: AnimesWebService
) : AnimesRemoteDataSource {
    override suspend fun requestAnimeListByFilter(filterName: String, page: Int): Result<List<Anime>> {
        return retrofitWrapper {
            animesWebService.getAnimeListByFilter(
                page = page, filter = filterName
            )
        }.map { response ->
            response.animeListResponse.map { animeResponse ->
                animeResponse.toDomain()
            }
        }
    }

    override suspend fun requestAnimeListByQuery(query: String, page: Int): Result<List<Anime>> {
        TODO("Not yet implemented")
    }
}
