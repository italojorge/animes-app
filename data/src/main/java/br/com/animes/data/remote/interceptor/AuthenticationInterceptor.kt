package br.com.animes.data.remote.interceptor

import br.com.animes.data.local.datasource.SessionDataSource
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor(
    private val sessionDataSource: SessionDataSource
) : Interceptor {
    private companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val session = sessionDataSource.getSession().orEmpty()
        val request = chain.request().newBuilder()

        return if (session.isNotBlank()) {
            chain.proceed(
                request.header(HEADER_AUTHORIZATION, "Bearer $session")
                    .build()
            )
        } else {
            chain.proceed(request.build())
        }
    }
}
