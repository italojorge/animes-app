package br.com.animes.feature.auth.domain.use.cases

import br.com.animes.domain.exception.EmptyFieldException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
@Suppress("ClassName")
class ValidateAppPasswordUseCaseTest {
    private val subject = ValidateAppPasswordUseCase()

    @Test
    fun `GIVEN a blank password WHEN execute use case THEN return failure with empty field exception`() = runBlockingTest {
        val result = subject.execute(ValidateAppPasswordUseCase.Params(password = ""))

        assertTrue(result.getErrorOrNull() is EmptyFieldException)
    }

    @Test
    fun `GIVEN a repeating password WHEN execute use case THEN return failure with Repeating password exception`() = runBlockingTest {
        val result = subject.execute(ValidateAppPasswordUseCase.Params(password = "1111"))

        assertTrue(result.getErrorOrNull() is RepeatingPasswordException)
    }

    @Test
    fun `GIVEN a password with length less than 4 WHEN execute use case THEN return failure with Invalid password exception`() = runBlockingTest {
        val result = subject.execute(ValidateAppPasswordUseCase.Params(password = "12"))

        assertTrue(result.getErrorOrNull() is InvalidPasswordException)
    }

    @Test
    fun `GIVEN a valid password WHEN execute use case THEN return success`() = runBlockingTest {
        val result = subject.execute(ValidateAppPasswordUseCase.Params(password = "4312"))

        assertTrue(result.isSuccess)
    }
}
