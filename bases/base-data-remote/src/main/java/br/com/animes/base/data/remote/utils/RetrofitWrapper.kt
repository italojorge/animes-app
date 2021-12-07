package br.com.animes.base.data.remote.utils

import br.com.animes.base.data.remote.model.ErrorResponse
import br.com.animes.domain.utils.Result
import com.google.gson.Gson
import okhttp3.ResponseBody
import org.koin.core.component.KoinComponent
import retrofit2.HttpException
import java.io.IOException

object RetrofitWrapper : KoinComponent {
//    private val analyticsEvent: AnalyticsEvent by inject()

    suspend fun <T> retrofitWrapper(
        call: suspend () -> T
    ): Result<T> {
        return try {
            Result.success(call())
        } catch (ioException: IOException) {
            Result.failure(ServerError(ErrorMessageEnum.INTERNET_ERROR.value))
        } catch (httpException: HttpException) {
            val errorBody = httpException.response()?.errorBody()
            Result.failure(getDataSourceException(errorBody, httpException))
        } catch (throwable: Throwable) {
//            analyticsEvent.recordException(throwable)
            Result.failure(ServerError())
        }
    }

    private fun getDataSourceException(
        errorBody: ResponseBody?,
        httpException: HttpException
    ): Throwable {
        return try {
            val error = errorBody?.string()?.let { Gson().fromJson<ErrorResponse>(it) }
            DataSourceException(
                errorCode = httpException.code(),
                message = error?.message ?: ErrorMessageEnum.DEFAULT_ERROR.value
            )
        } catch (e: Exception) {
            getGenericHttpError(httpException)
        }
    }

    private fun getGenericHttpError(
        httpException: HttpException
    ) = ServerHttpException(
        errorCode = httpException.code(),
        httpException = httpException,
        message = ErrorMessageEnum.DEFAULT_ERROR_WITH_CODE.value + httpException.code(),
    )
}
