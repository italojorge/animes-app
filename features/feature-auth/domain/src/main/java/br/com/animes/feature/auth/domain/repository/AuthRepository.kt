package br.com.animes.feature.auth.domain.repository

import br.com.animes.domain.utils.Result
import br.com.animes.feature.auth.domain.use.cases.UserCredentials

interface AuthRepository {
    suspend fun doLogin(user: String, password: String): Result<Unit>
    suspend fun doLogin(): Result<Unit>
    suspend fun saveCredentials(userCredentials: UserCredentials): Result<Unit>
    suspend fun hasCredentials(): Result<Boolean>
    suspend fun getUserEmail(): String?
}
