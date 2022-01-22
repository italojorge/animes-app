package br.com.animes.feature.auth.login

import androidx.lifecycle.Observer
import br.com.animes.core.utils.livedata.ViewState
import br.com.animes.domain.utils.Result
import br.com.animes.domain.utils.randomBoolean
import br.com.animes.domain.utils.randomString
import br.com.animes.feature.auth.domain.repository.AuthRepository
import br.com.animes.feature.auth.domain.use.cases.DoLoginUseCase
import br.com.animes.feature.auth.domain.use.cases.ValidateAppPasswordUseCase
import br.com.animes.feature.auth.domain.use.cases.ValidateUserEmailUseCase
import br.com.animes.test.utils.InstantExecutorExtension
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.justRun
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@Suppress("ClassName")
@ExtendWith(InstantExecutorExtension::class)
class LoginViewModelTest {
    private val validateUserEmailUseCase: ValidateUserEmailUseCase = mockk()
    private val validateAppPasswordUseCase: ValidateAppPasswordUseCase = mockk()
    private val doLoginUseCase: DoLoginUseCase = mockk()
    private val authRepository: AuthRepository = mockk()
    private val viewStateObserverMock: Observer<ViewState<Unit>> = mockk(relaxed = true)
    private lateinit var subject: LoginViewModel

    @BeforeEach
    fun setup() {
        justRun { viewStateObserverMock.onChanged(any()) }
        subject = LoginViewModel(
            validateUserEmailUseCase,
            validateAppPasswordUseCase,
            doLoginUseCase, authRepository
        )
        subject.loginViewState.observeForever(viewStateObserverMock)
    }

    @AfterEach
    fun clearMocks() {
        clearAllMocks()
    }

    @Nested
    inner class `GIVEN a call on doLogin without params` {
        @Test
        fun `WHEN auth repository returns success login THEN post success on liveData`() = runBlockingTest {
            coEvery { authRepository.doLogin() } returns Result.success(Unit)
            subject.doLogin()
            coVerifySequence {
                viewStateObserverMock.onChanged(ViewState.loading())
                viewStateObserverMock.onChanged(ViewState.success(Unit))
            }
        }

        @Test
        fun `WHEN auth repository returns a failure THEN post failure on liveData`() = runBlockingTest {
            val exception = Exception()

            coEvery { authRepository.doLogin() } returns Result.failure(exception)
            subject.doLogin()
            coVerifySequence {
                viewStateObserverMock.onChanged(ViewState.loading())
                viewStateObserverMock.onChanged(ViewState.failure(exception))
            }
        }
    }

    @Nested
    inner class `GIVEN a call on doLogin` {
        @Test
        fun `WHEN user is invalid THEN post throwable in user email error live data AND do not login`() = runBlockingTest {
            val userEmailException = Exception()
            val passwordException = Exception()
            coEvery { validateUserEmailUseCase.execute(any()) } returns Result.failure(userEmailException)
            coEvery { validateAppPasswordUseCase.execute(any()) } returns Result.failure(passwordException)
            subject.doLogin("", randomString, randomBoolean)
            assertEquals(userEmailException, subject.userEmailError.value)
            coVerify(exactly = 0) {
                doLoginUseCase.execute(any())
            }
        }

        @Test
        fun `WHEN password is invalid THEN post throwable in password error live data AND do not login`() = runBlockingTest {
            val userEmailException = Exception()
            val passwordException = Exception()
            coEvery { validateUserEmailUseCase.execute(any()) } returns Result.failure(userEmailException)
            coEvery { validateAppPasswordUseCase.execute(any()) } returns Result.failure(passwordException)
            subject.doLogin(randomString, "", randomBoolean)
            assertEquals(passwordException, subject.passwordError.value)
            coVerify(exactly = 0) {
                doLoginUseCase.execute(any())
            }
        }

        @Nested
        inner class `AND user and password are corrects` {
            @Test
            fun `WHEN login returns success THEN post success on login view state`() = runBlockingTest {
                coEvery { validateUserEmailUseCase.execute(any()) } returns Result.success(Unit)
                coEvery { validateAppPasswordUseCase.execute(any()) } returns Result.success(Unit)
                coEvery { doLoginUseCase.execute(any()) } returns Result.success(Unit)
                subject.doLogin(randomString, randomString, false)
                coVerifySequence {
                    viewStateObserverMock.onChanged(ViewState.loading())
                    viewStateObserverMock.onChanged(ViewState.success(Unit))
                }
            }

            @Test
            fun `WHEN login returns failure THEN post failure on login view state`() =
                runBlockingTest {
                    val exception = Exception()
                    coEvery { validateUserEmailUseCase.execute(any()) } returns Result.success(Unit)
                    coEvery { validateAppPasswordUseCase.execute(any()) } returns Result.success(Unit)
                    coEvery { doLoginUseCase.execute(any()) } returns Result.failure(exception)
                    subject.doLogin(randomString, randomString, randomBoolean)
                    coVerifySequence {
                        viewStateObserverMock.onChanged(ViewState.loading())
                        viewStateObserverMock.onChanged(ViewState.failure(exception))
                    }
                }

            @Test
            fun `WHEN need to save credentials AND login returns success THEN call repository to save credentials`() =
                runBlockingTest {
                    coEvery { validateUserEmailUseCase.execute(any()) } returns Result.success(Unit)
                    coEvery { validateAppPasswordUseCase.execute(any()) } returns Result.success(Unit)
                    coEvery { doLoginUseCase.execute(any()) } returns Result.success(Unit)
                    coJustRun { authRepository.saveCredentials(any()) }

                    subject.doLogin(randomString, randomString, true)
                    coVerify(exactly = 1) { authRepository.saveCredentials(any()) }
                }

            @Test
            fun `WHEN do not need to save credentials AND login returns success THEN not call repository to save credentials`() =
                runBlockingTest {
                    coEvery { validateUserEmailUseCase.execute(any()) } returns Result.success(Unit)
                    coEvery { validateAppPasswordUseCase.execute(any()) } returns Result.success(Unit)
                    coEvery { doLoginUseCase.execute(any()) } returns Result.success(Unit)
                    coJustRun { authRepository.saveCredentials(any()) }

                    subject.doLogin(randomString, randomString, false)
                    coVerify(exactly = 0) { authRepository.saveCredentials(any()) }
                }
        }
    }

    @Nested
    inner class `GIVEN a call on getUser` {
        @Test
        fun `WHEN auth repository returns null user email THEN post empty user email on liveData`() = runBlockingTest {
            val userEmail = null
            coEvery { authRepository.getUserEmail() } returns Result.success(userEmail)
            subject.getUser()
            assertEquals("", subject.userEmail.value)
        }

        @Test
        fun `WHEN auth repository returns a valid user email THEN post user email on liveData`() = runBlockingTest {
            val userEmail = randomString
            coEvery { authRepository.getUserEmail() } returns Result.success(userEmail)
            subject.getUser()
            assertEquals(userEmail, subject.userEmail.value)
        }
    }

    @Nested
    inner class `GIVEN a call on checkCredentials` {
        @Test
        fun `WHEN repository returns success AND has credentials THEN post true on liveData`() = runBlockingTest {
            val hasCredentials = true
            coEvery { authRepository.hasCredentials() } returns Result.success(hasCredentials)
            subject.checkCredentials()
            assertEquals(hasCredentials, subject.hasCredentials.value)
        }

        @Test
        fun `WHEN repository returns success AND has not credentials THEN post false on liveData`() = runBlockingTest {
            val hasCredentials = false
            coEvery { authRepository.hasCredentials() } returns Result.success(hasCredentials)
            subject.checkCredentials()
            assertEquals(hasCredentials, subject.hasCredentials.value)
        }
    }
}
