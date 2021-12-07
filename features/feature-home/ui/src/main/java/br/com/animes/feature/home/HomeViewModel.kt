package br.com.animes.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.animes.feature.home.domain.model.Anime
import br.com.animes.feature.home.domain.model.FilterTopAnimesEnum
import br.com.animes.feature.home.domain.repository.AnimesRepository
import kotlinx.coroutines.flow.Flow

class HomeViewModel(private val repository: AnimesRepository) : ViewModel() {
    private var currentQueryValue: String? = null

    private var currentSearchResult: Flow<PagingData<Anime>>? = null

    fun getAnimesByQuery(queryString: String): Flow<PagingData<Anime>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString
        val newResult: Flow<PagingData<Anime>> = repository.getAnimesByQuery(currentQueryValue ?: queryString)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

    fun getAnimesByFilter(filterTopAnimesBindingEnum: FilterTopAnimesBindingEnum): Flow<PagingData<Anime>> {
        return repository.getAnimesByFilter(
            FilterTopAnimesEnum.valueOf(filterTopAnimesBindingEnum.name)
        ).cachedIn(viewModelScope)
    }
}
