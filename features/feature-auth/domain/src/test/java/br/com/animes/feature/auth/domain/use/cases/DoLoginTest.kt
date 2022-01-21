package br.com.animes.feature.auth.domain.use.cases

import br.com.animes.domain.utils.Result
import br.com.animes.domain.utils.randomString
import br.com.animes.feature.auth.domain.model.UserCredentials
import br.com.animes.feature.auth.domain.repository.AuthRepository
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
@Suppress("ClassName")
class DoLoginTest {
    private val validateUserEmailUseCase = mockk<ValidateUserEmailUseCase>()
    private val validateAppPasswordUseCase = mockk<ValidateAppPasswordUseCase>()
    private val authRepository = mockk<AuthRepository>()
    private val subject = DoLoginUseCase(validateUserEmailUseCase, validateAppPasswordUseCase, authRepository)

    @AfterEach
    fun clearMocks() {
        clearAllMocks()
    }

    @Test
    fun `GIVEN an invalid password WHEN execute use case THEN return failure with login invalid credentials exception`() =
        runBlockingTest {
            coEvery { validateAppPasswordUseCase.execute(any()) } returns Result.failure(Exception())
            coEvery { validateUserEmailUseCase.execute(any()) } returns Result.success(Unit)

            val result = subject.execute(UserCredentials(email = randomString, password = randomString))

            assertTrue(result.getErrorOrNull() is LoginInvalidCredentialsException)
        }

    @Test
    fun `GIVEN an invalid email WHEN execute use case THEN return failure with login invalid credentials exception`() = runBlockingTest {
        coEvery { validateUserEmailUseCase.execute(any()) } returns Result.failure(Exception())
        coEvery { validateAppPasswordUseCase.execute(any()) } returns Result.success(Unit)

        val result = subject.execute(UserCredentials(email = randomString, password = randomString))

        assertTrue(result.getErrorOrNull() is LoginInvalidCredentialsException)
    }

    @Test
    fun `GIVEN valid params WHEN execute use case THEN call login on repository`() = runBlockingTest {
        val email = randomString
        val password = randomString

        coEvery { validateUserEmailUseCase.execute(any()) } returns Result.success(Unit)
        coEvery { validateAppPasswordUseCase.execute(any()) } returns Result.success(Unit)
        coEvery { authRepository.doLogin(any(), any()) } returns Result.success(Unit)

        subject.execute(UserCredentials(email = email, password = password))

        coVerify(exactly = 1) { authRepository.doLogin(email, password) }
    }

    @Test
    fun `GIVEN valid params WHEN execute use case THEN return repository result`() = runBlockingTest {
        coEvery { validateUserEmailUseCase.execute(any()) } returns Result.success(Unit)
        coEvery { validateAppPasswordUseCase.execute(any()) } returns Result.success(Unit)
        coEvery { authRepository.doLogin(any(), any()) } returns Result.success(Unit)

        val result = subject.execute(UserCredentials(email = randomString, password = randomString))

        assertTrue(result.isSuccess)
    }
}
