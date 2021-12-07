package br.com.animes.feature.home.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.animes.core.utils.livedata.SingleLiveDataEvent
import br.com.animes.core.utils.livedata.ViewState
import br.com.animes.core.utils.livedata.postFailure
import br.com.animes.core.utils.livedata.postLoading
import br.com.animes.core.utils.livedata.postSuccess
import br.com.animes.feature.home.domain.model.AnimeDetails
import br.com.animes.feature.home.domain.repository.AnimesRepository
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnimeDetailsViewModel(
    private val repository: AnimesRepository,
    private val dispatcher: CoroutineContext = Dispatchers.Main
) : ViewModel() {
    private val _viewState = SingleLiveDataEvent<ViewState<AnimeDetails>>()
    val viewState: LiveData<ViewState<AnimeDetails>> = _viewState

    fun getAnimesDetails(id: Long) {
        viewModelScope.launch(dispatcher) {
            _viewState.postLoading()
            repository.getAnimeDetails(id).onSuccess {
                _viewState.postSuccess(it)
            }.onFailure {
                _viewState.postFailure(it)
            }
        }
    }
}
