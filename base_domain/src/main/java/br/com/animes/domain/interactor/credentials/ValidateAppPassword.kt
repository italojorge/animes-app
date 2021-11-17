package br.com.animes.domain.interactor.credentials

import br.com.animes.domain.core.UseCase
import br.com.animes.domain.exception.EmptyFieldException
import br.com.animes.domain.utils.Result
import br.com.animes.domain.utils.isRepeating

class ValidateAppPassword : UseCase<ValidateAppPassword.Params, Unit>() {
    override suspend fun execute(param: Params): Result<Unit> {
        return when {
            param.password.isBlank() -> Result.failure(EmptyFieldException())
            param.password.isRepeating() -> Result.failure(RepeatingPasswordException())
            param.passwordConfirmation.isBlank() -> Result.failure(EmptyFieldException())
            param.password != param.passwordConfirmation -> Result.failure(PasswordNotMatchException())
            else -> Result.success(Unit)
        }
    }

    data class Params(
        val password: String,
        val passwordConfirmation: String = password
    )
}