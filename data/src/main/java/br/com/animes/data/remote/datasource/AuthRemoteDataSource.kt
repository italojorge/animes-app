package br.com.animes.data.remote.datasource

import br.com.animes.domain.utils.Result

interface AuthRemoteDataSource {
    suspend fun doLogin(user: String, password: String): Result<String>
}
