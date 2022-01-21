package br.com.animes.feature.auth.domain.use.cases

import br.com.animes.domain.core.UseCase
import br.com.animes.domain.exception.EmptyFieldException
import br.com.animes.domain.utils.Result
import br.com.animes.domain.utils.isRepeating

class ValidateAppPasswordUseCase : UseCase<ValidateAppPasswordUseCase.Params, Unit>() {
    override suspend fun execute(param: Params): Result<Unit> {
        return when {
            param.password.isBlank() -> Result.failure(EmptyFieldException())
            param.password.isRepeating() -> Result.failure(RepeatingPasswordException())
            param.password.length < PASSWORD_MIN_LENGTH -> Result.failure(InvalidPasswordException())
            else -> Result.success(Unit)
        }
    }

    data class Params(
        val password: String
    )

    private companion object {
        const val PASSWORD_MIN_LENGTH = 4
    }
}