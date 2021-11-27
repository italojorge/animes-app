package br.com.animes.feature.auth.domain.use.cases

import br.com.animes.domain.core.UseCase
import br.com.animes.domain.exception.EmptyFieldException
import br.com.animes.domain.utils.Result

class ValidateUser : UseCase<ValidateUser.Params, Unit>() {
    override suspend fun execute(param: Params): Result<Unit> {
        return when {
            param.user.isBlank() -> Result.failure(EmptyFieldException())
            else -> Result.success(Unit)
        }
    }

    data class Params(
        val user: String
    )
}
