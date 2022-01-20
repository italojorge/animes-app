package br.com.animes.feature.auth.data

import br.com.animes.domain.utils.Result
import br.com.animes.feature.auth.domain.model.UserCredentials

interface AuthLocalDataSource {
    fun saveSession(sessionData: String)
    fun saveCredentials(userCredentials: UserCredentials): Result<Unit>
    fun getCredentials(): Result<UserCredentials?>
    fun hasCredentials(): Result<Boolean>
    fun saveUserEmail(username: String)
    fun getUserEmail(): Result<String?>
}