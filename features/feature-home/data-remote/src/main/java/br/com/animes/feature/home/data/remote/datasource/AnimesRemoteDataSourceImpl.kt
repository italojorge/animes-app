package br.com.animes.feature.home.data.remote.datasource

import br.com.animes.base.data.remote.utils.RetrofitWrapper.retrofitWrapper
import br.com.animes.domain.utils.Result
import br.com.animes.feature.home.data.AnimesRemoteDataSource
import br.com.animes.feature.home.data.remote.mapper.toDomain
import br.com.animes.feature.home.data.remote.service.AnimesWebService
import br.com.animes.feature.home.domain.model.Anime
import br.com.animes.feature.home.domain.model.AnimeDetails
import br.com.animes.feature.home.domain.model.FilterTopAnimesEnum

class AnimesRemoteDataSourceImpl(
    private val animesWebService: AnimesWebService
) : AnimesRemoteDataSource {
    override suspend fun requestAnimeListByFilter(filter: FilterTopAnimesEnum, page: Int): Result<List<Anime>> {
        return retrofitWrapper {
            animesWebService.getAnimeListByFilter(
                page = page, filter = filter.name.lowercase()
            )
        }.map { response ->
            response.animeListResponse.map { animeResponse ->
                animeResponse.toDomain()
            }
        }
    }

    override suspend fun requestAnimeListByQuery(query: String, page: Int): Result<List<Anime>> {
        return retrofitWrapper {
            animesWebService.getAnimeListByQuery(query, page)
        }.map { response ->
            response.animeListResponse.map { animeResponse ->
                animeResponse.toDomain()
            }
        }
    }

    override suspend fun getAnimeDetails(id: Long): Result<AnimeDetails> {
        return retrofitWrapper {
            animesWebService.getAnimeDetails(id)
        }.map { response ->
            response.toDomain()
        }
    }
}
