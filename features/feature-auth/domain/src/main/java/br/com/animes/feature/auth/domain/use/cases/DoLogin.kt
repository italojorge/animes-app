package br.com.animes.feature.auth.domain.use.cases

import br.com.animes.domain.core.UseCase
import br.com.animes.feature.auth.domain.repository.AuthRepository
import br.com.animes.domain.utils.Result

class DoLogin(
    private val validateUserEmailUseCase: ValidateUserEmail,
    private val validateAppPasswordUseCase: ValidateAppPassword,
    private val authRepository: AuthRepository
) : UseCase<UserCredentials, Unit>() {
    override suspend fun execute(param: UserCredentials): Result<Unit> {
        val validatePasswordResult = validateAppPasswordUseCase.execute(
            ValidateAppPassword.Params(password = param.password)
        )
        val validateUserResult = validateUserEmailUseCase.execute(
            ValidateUserEmail.Params(email = param.email)
        )
        return if (validatePasswordResult.isSuccess && validateUserResult.isSuccess) {
            authRepository.doLogin(param.email, param.password)
        } else {
            Result.failure(LoginInvalidCredentialsException())
        }
    }
}
