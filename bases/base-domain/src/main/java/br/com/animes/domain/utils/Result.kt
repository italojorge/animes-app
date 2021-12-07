package br.com.animes.domain.utils

class Result<T> private constructor(
    private val success: T?,
    private val failure: Throwable?
) {
    val isSuccess: Boolean get() = failure == null

    val isFailure: Boolean get() = failure != null

    suspend fun onSuccess(
        action: suspend (T) -> Unit
    ): Result<T> {
        success?.let { action(it) }
        return this
    }

    suspend fun onFailure(
        action: suspend (Throwable) -> Unit
    ): Result<T> {
        failure?.let { action(it) }
        return this
    }

    suspend fun onAny(
        action: suspend (Result<T>) -> Unit
    ): Result<T> {
        success?.let { action(this) }
        failure?.let { action(this) }
        return this
    }

    suspend fun mapError(
        action: suspend (Throwable) -> Throwable
    ): Result<T> {
        if (failure == null) return this

        return failure(
            action(failure)
        )
    }

    suspend fun <V> flatMap(
        action: suspend (T) -> Result<V>
    ): Result<V> {
        if (success == null) return Result(success, failure)

        return action(success)
    }

    suspend fun <V> map(action: suspend (T) -> V): Result<V> {
        if (success == null) return Result(success, failure)

        return success(
            action(success)
        )
    }

    fun mapToUnit(): Result<Unit> {
        if (success == null) return Result(success, failure)
        return success(Unit)
    }

    fun getOrNull(): T? = success

    fun getErrorOrNull(): Throwable? = failure

    companion object {
        fun <T> success(data: T) = Result(success = data, failure = null)
        fun <T> failure(throwable: Throwable) = Result<T>(success = null, failure = throwable)
    }
}
