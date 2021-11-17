package br.com.animes.data.local.datasource

import br.com.animes.domain.interactor.credentials.UserCredentials
import br.com.animes.domain.utils.Result

interface SessionDataSource {
    fun saveSession(sessionData: String)
    fun saveCredentials(userCredentials: UserCredentials): Result<Unit>
    fun getSession(): String?
    fun getCredentials(): Result<UserCredentials?>
    fun hasCredentials(): Result<Boolean>
    fun deleteAllSessionData()
    fun saveUserEmail(username: String)
    fun getUserEmail(): String?
}

