package br.com.animes.base.feature.utils.livedata

import androidx.lifecycle.MutableLiveData

class ViewState<T> private constructor(
    val status: ViewStatus = ViewStatus.SUCCESS,
    val data: T? = null,
    val failure: Throwable? = null
) {

    companion object {
        fun <T> success(data: T): ViewState<T> = ViewState(ViewStatus.SUCCESS, data)

        fun <T> loading(): ViewState<T> = ViewState(ViewStatus.LOADING)

        fun <T> failure(failure: Throwable): ViewState<T> = ViewState(ViewStatus.FAILURE, failure = failure)
    }

    fun handleIt(
        onSuccess: (T) -> Unit = {},
        onFailure: (Throwable) -> Unit = {},
        isLoading: (Boolean) -> Unit = {}
    ): ViewState<T> {
        when (status) {
            ViewStatus.SUCCESS -> {
                this.data?.let { onSuccess(it) }
                isLoading(false)
            }
            ViewStatus.LOADING -> isLoading(true)
            ViewStatus.FAILURE -> {
                this.failure?.let { onFailure(it) }
                isLoading(false)
            }
        }
        return this
    }

    override fun equals(other: Any?): Boolean {
        return if (other is ViewState<*>) {
            other.data == this.data && other.failure == this.failure && other.status == this.status
        } else {
            false
        }
    }
}

enum class ViewStatus {
    LOADING, SUCCESS, FAILURE
}

fun <T> MutableLiveData<ViewState<T>>.postSuccess(data: T) = postValue(ViewState.success(data))

fun <T> MutableLiveData<ViewState<T>>.postLoading() = postValue(ViewState.loading())

fun <T> MutableLiveData<ViewState<T>>.postFailure(failure: Throwable) = postValue(ViewState.failure(failure))
