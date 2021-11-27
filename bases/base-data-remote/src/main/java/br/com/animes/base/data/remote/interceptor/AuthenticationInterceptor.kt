package br.com.animes.base.data.remote.interceptor

import br.com.animes.base.data.remote.factory.UserSessionManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor(
    private val userSessionManager: UserSessionManager
) : Interceptor {
    private companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val session = userSessionManager.getSession().orEmpty()
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
