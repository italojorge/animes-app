package br.com.animes.domain.interactor.credentials

import br.com.animes.domain.core.UseCase
import br.com.animes.domain.repository.AuthRepository
import br.com.animes.domain.utils.Result

class DoLogin(
    private val validateUserUseCase: ValidateUser,
    private val validateAppPasswordUseCase: ValidateAppPassword,
    private val authRepository: AuthRepository
) :
    UseCase<UserCredentials, Unit>() {
    override suspend fun execute(param: UserCredentials): Result<Unit> {
        val validatePasswordResult = validateAppPasswordUseCase.execute(
            ValidateAppPassword.Params(password = param.password)
        )
        val validateUserResult = validateUserUseCase.execute(
            ValidateUser.Params(user = param.user)
        )
        return if (validatePasswordResult.isSuccess && validateUserResult.isSuccess) {
            authRepository.doLogin(param.user, param.password)
        } else {
            Result.failure(LoginInvalidCredentialsException())
        }
    }
}
