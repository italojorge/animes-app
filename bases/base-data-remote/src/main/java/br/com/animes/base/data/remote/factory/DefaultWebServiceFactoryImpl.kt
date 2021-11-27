package br.com.animes.base.data.remote.factory

import br.com.animes.base.data.remote.interceptor.AuthenticationInterceptor
import br.com.animes.base.data.remote.utils.CONNECTION_TIME_OUT
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class DefaultWebServiceFactoryImpl(
    private val baseUrl: String,
    private val userSessionManager: UserSessionManager,
    private val isDebugModeOn: Boolean
) : WebServiceFactory {
    override fun <T> createWebService(
        service: Class<T>
    ): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(service)
    }

    private fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .apply {
                if (isDebugModeOn)
                    addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
            .addInterceptor(AuthenticationInterceptor(userSessionManager))
            .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .build()
}
