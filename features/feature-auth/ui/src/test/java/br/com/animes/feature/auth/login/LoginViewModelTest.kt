package br.com.animes.feature.auth.login

import androidx.lifecycle.Observer
import br.com.animes.domain.utils.Result
import br.com.animes.feature.auth.InstantExecutorExtension
import br.com.animes.feature.auth.domain.repository.AuthRepository
import br.com.animes.feature.auth.domain.use.cases.DoLogin
import br.com.animes.feature.auth.domain.use.cases.ValidateAppPassword
import br.com.animes.feature.auth.domain.use.cases.ValidateUserEmail
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExperimentalCoroutinesApi
@Suppress("ClassName")
@ExtendWith(InstantExecutorExtension::class)
class LoginViewModelTest {
    private val validateUserEmailUseCase: ValidateUserEmail = mockk()
    private val validateAppPasswordUseCase: ValidateAppPassword = mockk()
    private val doLoginUseCase: DoLogin = mockk()
    private val authRepository: AuthRepository = mockk()
    private val observerLoading: Observer<Boolean> = mockk(relaxed = true)
    private lateinit var subject: LoginViewModel
    val randomString: String
        get() = UUID.randomUUID().toString()

    @BeforeEach
    fun setup() {
        subject = LoginViewModel(
            validateUserEmailUseCase,
            validateAppPasswordUseCase,
            doLoginUseCase, authRepository, TestCoroutineDispatcher()
        )
    }

    @AfterEach
    fun clearMocks() {
        clearAllMocks()
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
}
