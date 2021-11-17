package br.com.animes.domain.repository

import br.com.animes.domain.interactor.credentials.UserCredentials
import br.com.animes.domain.utils.Result

interface AuthRepository {
    suspend fun doLogin(user: String, password: String): Result<Unit>
    suspend fun doLogin(): Result<Unit>
    suspend fun saveCredentials(userCredentials: UserCredentials): Result<Unit>
    suspend fun hasCredentials(): Result<Boolean>
    suspend fun getUserEmail(): String?
}
