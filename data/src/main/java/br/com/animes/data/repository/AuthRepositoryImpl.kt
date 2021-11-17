package br.com.animes.data.repository

import br.com.animes.data.local.datasource.SessionDataSource
import br.com.animes.data.remote.datasource.AuthRemoteDataSource
import br.com.animes.domain.interactor.credentials.UserCredentials
import br.com.animes.domain.repository.AuthRepository
import br.com.animes.domain.utils.Result

class AuthRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val sessionDataSource: SessionDataSource
) : AuthRepository {
    override suspend fun doLogin(user: String, password: String): Result<Unit> {
        return handleLoginResult(user, password)
    }

    override suspend fun doLogin(): Result<Unit> {
        val userCredentials = sessionDataSource.getCredentials().getOrNull()
        val loginResult = authRemoteDataSource.doLogin(
            userCredentials?.user.orEmpty(),
            userCredentials?.password.orEmpty()
        )
        if (loginResult.isSuccess) sessionDataSource.saveSession(loginResult.getOrNull().orEmpty())
        return loginResult.mapToUnit()
    }

    override suspend fun saveCredentials(userCredentials: UserCredentials): Result<Unit> {
        return sessionDataSource.saveCredentials(userCredentials)
    }

    override suspend fun hasCredentials(): Result<Boolean> {
        return sessionDataSource.hasCredentials()
    }

    override suspend fun getUserEmail(): String? {
        return sessionDataSource.getUserEmail()
    }

    private suspend fun handleLoginResult(
        user: String,
        password: String
    ): Result<Unit> {
        val loginResult = authRemoteDataSource.doLogin(user, password)
        if (loginResult.isSuccess) {
            sessionDataSource.saveSession(loginResult.getOrNull().orEmpty())
            sessionDataSource.saveUserEmail(user)
        }
        return loginResult.mapToUnit()
    }
}
