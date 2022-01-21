package br.com.animes.feature.auth.domain.use.cases

import br.com.animes.domain.exception.EmptyFieldException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
@Suppress("ClassName")
class ValidateUserEmailUseCaseTest {
    private val subject = ValidateUserEmailUseCase()

    @Test
    fun `GIVEN a blank email WHEN execute use case THEN return failure with empty field exception`() = runBlockingTest {
        val result = subject.execute(ValidateUserEmailUseCase.Params(email = ""))

        assertTrue(result.getErrorOrNull() is EmptyFieldException)
    }

    @Test
    fun `GIVEN an invalid email WHEN execute use case THEN return failure with invalid email exception`() = runBlockingTest {
        val result = subject.execute(ValidateUserEmailUseCase.Params(email = "test"))

        assertTrue(result.getErrorOrNull() is InvalidEmailException)
    }

    @Test
    fun `GIVEN a valid email WHEN execute use case THEN return success`() = runBlockingTest {
        val result = subject.execute(ValidateUserEmailUseCase.Params(email = "test@gmail.com"))

        assertTrue(result.isSuccess)
    }
}
