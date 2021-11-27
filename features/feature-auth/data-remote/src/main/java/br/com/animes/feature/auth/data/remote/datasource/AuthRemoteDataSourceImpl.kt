package br.com.animes.feature.auth.data.remote.datasource

import br.com.animes.base.data.remote.utils.RetrofitWrapper.retrofitWrapper
import br.com.animes.domain.utils.Result
import br.com.animes.feature.auth.data.AuthRemoteDataSource
import br.com.animes.feature.auth.data.remote.service.LoginWebService
import okhttp3.Credentials

class AuthRemoteDataSourceImpl(
    private val loginWebService: LoginWebService
) : AuthRemoteDataSource {
    override suspend fun doLogin(user: String, password: String): Result<String> {
        return retrofitWrapper {
            loginWebService.doLogin(
                basicAuth = Credentials.basic(user, password)
            )
        }.map {
            it.bearerToken.orEmpty()
        }
    }
}
