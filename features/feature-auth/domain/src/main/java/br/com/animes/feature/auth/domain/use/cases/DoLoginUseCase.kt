package br.com.animes.feature.auth.domain.use.cases

import br.com.animes.domain.core.UseCase
import br.com.animes.domain.utils.Result
import br.com.animes.feature.auth.domain.model.UserCredentials
import br.com.animes.feature.auth.domain.repository.AuthRepository

class DoLoginUseCase(
    private val validateUserEmailUseCase: UseCase<ValidateUserEmailUseCase.Params, Unit>,
    private val validateAppPasswordUseCase: UseCase<ValidateAppPasswordUseCase.Params, Unit>,
    private val authRepository: AuthRepository
) : UseCase<UserCredentials, Unit>() {
    override suspend fun execute(param: UserCredentials): Result<Unit> {
        val validatePasswordResult = validateAppPasswordUseCase.execute(
            ValidateAppPasswordUseCase.Params(password = param.password)
        )
        val validateUserResult = validateUserEmailUseCase.execute(
            ValidateUserEmailUseCase.Params(email = param.email)
        )
        return if (validatePasswordResult.isSuccess && validateUserResult.isSuccess) {
            authRepository.doLogin(param.email, param.password)
        } else {
            Result.failure(LoginInvalidCredentialsException())
        }
    }
}
