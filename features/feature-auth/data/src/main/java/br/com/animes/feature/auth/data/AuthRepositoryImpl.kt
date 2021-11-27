package br.com.animes.feature.auth.data

import br.com.animes.domain.utils.Result
import br.com.animes.feature.auth.domain.repository.AuthRepository
import br.com.animes.feature.auth.domain.use.cases.UserCredentials

class AuthRepositoryImpl(
    private val remoteDataSource: AuthRemoteDataSource,
    private val localDataSource: AuthLocalDataSource
) : AuthRepository {
    override suspend fun doLogin(user: String, password: String): Result<Unit> {
        return handleLoginResult(user, password)
    }

    override suspend fun doLogin(): Result<Unit> {
        val userCredentials = localDataSource.getCredentials().getOrNull()
        val loginResult = remoteDataSource.doLogin(
            userCredentials?.email.orEmpty(),
            userCredentials?.password.orEmpty()
        )
        if (loginResult.isSuccess) localDataSource.saveSession(loginResult.getOrNull().orEmpty())
        return loginResult.mapToUnit()
    }

    override suspend fun saveCredentials(userCredentials: UserCredentials): Result<Unit> {
        return localDataSource.saveCredentials(userCredentials)
    }

    override suspend fun hasCredentials(): Result<Boolean> {
        return localDataSource.hasCredentials()
    }

    override suspend fun getUserEmail(): String? {
        return localDataSource.getUserEmail()
    }

    private suspend fun handleLoginResult(
        user: String,
        password: String
    ): Result<Unit> {
        val loginResult = remoteDataSource.doLogin(user, password)
        if (loginResult.isSuccess) {
            localDataSource.saveSession(loginResult.getOrNull().orEmpty())
            localDataSource.saveUserEmail(user)
        }
        return loginResult.mapToUnit()
    }
}
