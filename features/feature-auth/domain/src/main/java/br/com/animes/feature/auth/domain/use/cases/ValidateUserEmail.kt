package br.com.animes.feature.auth.domain.use.cases

import br.com.animes.domain.core.UseCase
import br.com.animes.domain.exception.EmptyFieldException
import br.com.animes.domain.utils.Result
import br.com.animes.domain.utils.isEmail

class ValidateUserEmail : UseCase<ValidateUserEmail.Params, Unit>() {
    override suspend fun execute(param: Params): Result<Unit> {
        return when {
            param.email.isBlank() -> Result.failure(EmptyFieldException())
            param.email.isEmail() -> Result.failure(InvalidEmailException())
            else -> Result.success(Unit)
        }
    }

    data class Params(
        val email: String
    )
}
