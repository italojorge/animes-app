package br.com.animes.feature.auth.data

import br.com.animes.domain.utils.Result

interface AuthRemoteDataSource {
    suspend fun doLogin(user: String, password: String): Result<String>
}
