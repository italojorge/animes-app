package br.com.animes.feature.auth.login

import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import br.com.animes.domain.exception.EmptyFieldException
import br.com.animes.domain.utils.Result
import br.com.animes.domain.utils.randomString
import br.com.animes.feature.auth.biometric.AuthenticationResult
import br.com.animes.feature.auth.domain.use.cases.InvalidEmailException
import br.com.animes.feature.auth.domain.use.cases.InvalidPasswordException
import br.com.animes.feature.auth.login.di.LoginTestDependencies
import br.com.animes.feature.auth.login.robot.onLaunch
import br.com.animes.test.utils.InstantExecutorExtension
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.invoke
import io.mockk.justRun
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import org.koin.test.junit5.KoinTestExtension

@ExtendWith(InstantExecutorExtension::class)
@Suppress("ClassName")
class LoginFragmentTest {
    val navController by lazy { TestNavHostController(ApplicationProvider.getApplicationContext()) }

    @JvmField
    @RegisterExtension
    val koinTest = KoinTestExtension.create {
        allowOverride(true)
        androidLogger(Level.ERROR)
        androidContext(ApplicationProvider.getApplicationContext())
        modules(LoginTestDependencies.testModules)
    }

    @BeforeEach
    fun setup() {
        coEvery { LoginTestDependencies.authRepository.getUserEmail() } returns Result.success(null)
        coEvery { LoginTestDependencies.authRepository.hasCredentials() } returns Result.success(false)
        coEvery { LoginTestDependencies.biometricChecker.hasBiometrics() } returns false
    }

    @AfterEach
    fun clearMocks() {
        clearAllMocks()
    }

    @Nested
    inner class When_start_screen {
        @Test
        fun then_show_correct_texts() {
            onLaunch().checkIf {
                isShowingCorrectTexts()
            }
        }

        @Test
        fun and_has_a_saved_user_email_then_fill_user_email_edit_text() {
            val userEmail = randomString
            coEvery { LoginTestDependencies.authRepository.getUserEmail() } returns Result.success(userEmail)

            onLaunch().checkIf {
                checkTextOnEmailEditText(userEmail)
            }
        }

        @Test
        fun and_has_not_a_saved_user_email_then_user_email_edit_text_remains_empty() {
            val userEmail = null
            coEvery { LoginTestDependencies.authRepository.getUserEmail() } returns Result.success(userEmail)

            onLaunch().checkIf {
                checkTextOnEmailEditText("")
            }
        }
    }

    @Nested
    inner class Given_a_biometric_authentication_when_start_screen {
        @Test
        fun and_user_has_biometric_on_device_and_has_not_credentials_saved_then_show_switch_to_use_login_with_biometric() {
            coEvery { LoginTestDependencies.biometricChecker.hasBiometrics() } returns true
            coEvery { LoginTestDependencies.authRepository.hasCredentials() } returns Result.success(false)

            onLaunch().checkIf {
                isBiometricSwitchDisplayed()
            }
        }

        @Test
        fun and_user_has_not_biometric_on_device_or_user_has_credentials_saved_then_not_show_biometric_switch() {
            coEvery { LoginTestDependencies.biometricChecker.hasBiometrics() } returns false
            coEvery { LoginTestDependencies.authRepository.hasCredentials() } returns Result.success(false)

            onLaunch().checkIf {
                isBiometricSwitchNotDisplayed()
            }
        }

        @Test
        fun and_user_has_credentials_saved_then_show_biometric_authentication() {
            coEvery { LoginTestDependencies.authRepository.hasCredentials() } returns Result.success(true)
            justRun { LoginTestDependencies.biometricAuth.authenticate(any()) }

            onLaunch().checkIf {
                verify(exactly = 1) { LoginTestDependencies.biometricAuth.authenticate(any()) }
            }
        }

        @Test
        fun and_user_do_login_with_biometric_then_should_do_login_request() {
            coEvery { LoginTestDependencies.authRepository.hasCredentials() } returns Result.success(true)
            every { LoginTestDependencies.biometricAuth.authenticate(captureLambda()) } answers {
                lambda<(AuthenticationResult) -> Unit>().invoke(AuthenticationResult.SUCCESS)
            }
            coEvery { LoginTestDependencies.authRepository.doLogin() } returns Result.success(Unit)
            justRun { LoginTestDependencies.authNavigation.navigateToHome() }

            onLaunch().checkIf {
                verify(exactly = 1) { LoginTestDependencies.biometricAuth.authenticate(any()) }
                coVerify(exactly = 1) { LoginTestDependencies.authRepository.doLogin() }
            }
        }
    }

    @Nested
    inner class Given_invalid_credentials {
        @Test
        fun when_user_email_is_wrong_then_show_error_message_in_user_email_field() {
            val invalidEmailException = InvalidEmailException()

            coEvery { LoginTestDependencies.biometricChecker.hasBiometrics() } returns true
            coEvery { LoginTestDependencies.authRepository.hasCredentials() } returns Result.success(false)
            coEvery { LoginTestDependencies.validateAppPasswordUseCase.execute(any()) } returns Result.success(Unit)
            coEvery { LoginTestDependencies.validateUserEmailUseCase.execute(any()) } returns Result.failure(invalidEmailException)
            justRun { LoginTestDependencies.authNavigation.navigateToHome() }

            onLaunch {
                typeAnEmail("test")
                typeAPassword("213521")
                clickOnEnterButton()
            }.checkIf {
                hasErrorTextOnUserEmailTextInputLayout("Invalid E-mail.")
            }
        }

        @Test
        fun when_password_is_wrong_then_show_error_message_in_password_field() {
            val invalidPasswordException = InvalidPasswordException()

            coEvery { LoginTestDependencies.biometricChecker.hasBiometrics() } returns true
            coEvery { LoginTestDependencies.authRepository.hasCredentials() } returns Result.success(false)
            coEvery { LoginTestDependencies.validateAppPasswordUseCase.execute(any()) } returns Result.failure(invalidPasswordException)
            coEvery { LoginTestDependencies.validateUserEmailUseCase.execute(any()) } returns Result.success(Unit)

            onLaunch {
                typeAnEmail("test@gmail.com")
                typeAPassword("123")
                clickOnEnterButton()
            }.checkIf {
                hasErrorTextOnPasswordTextInputLayout("Invalid password.")
            }
        }

        @Test
        fun when_user_email_and_password_are_wrong_then_show_error_message_in_both_fields() {
            val emptyFieldException = EmptyFieldException()
            val invalidPasswordException = InvalidPasswordException()

            coEvery { LoginTestDependencies.biometricChecker.hasBiometrics() } returns true
            coEvery { LoginTestDependencies.authRepository.hasCredentials() } returns Result.success(false)
            coEvery { LoginTestDependencies.validateAppPasswordUseCase.execute(any()) } returns Result.failure(invalidPasswordException)
            coEvery { LoginTestDependencies.validateUserEmailUseCase.execute(any()) } returns Result.failure(emptyFieldException)

            onLaunch {
                typeAnEmail("")
                typeAPassword("123")
                clickOnEnterButton()
            }.checkIf {
                hasErrorTextOnUserEmailTextInputLayout("Required field.")
                hasErrorTextOnPasswordTextInputLayout("Invalid password.")
            }
        }
    }

    @Nested
    inner class Given_valid_credentials {
        @Test
        fun when_login_request_is_success_then_should_navigate_to_home() {
            coEvery { LoginTestDependencies.biometricChecker.hasBiometrics() } returns true
            coEvery { LoginTestDependencies.authRepository.hasCredentials() } returns Result.success(false)
            coEvery { LoginTestDependencies.validateAppPasswordUseCase.execute(any()) } returns Result.success(Unit)
            coEvery { LoginTestDependencies.validateUserEmailUseCase.execute(any()) } returns Result.success(Unit)
            coEvery { LoginTestDependencies.doLoginUseCase.execute(any()) } returns Result.success(Unit)
            justRun { LoginTestDependencies.authNavigation.navigateToHome() }

            onLaunch {
                typeAnEmail("test@gmail.com")
                typeAPassword("213521")
                clickOnEnterButton()
            }.checkIf {
                verify(exactly = 1) { LoginTestDependencies.authNavigation.navigateToHome() }
            }
        }

        @Test
        fun when_login_request_is_failure_then_should_show_an_error_dialog_with_throwable_message() {
            val exception = Exception(randomString)

            coEvery { LoginTestDependencies.biometricChecker.hasBiometrics() } returns true
            coEvery { LoginTestDependencies.authRepository.hasCredentials() } returns Result.success(false)
            coEvery { LoginTestDependencies.validateAppPasswordUseCase.execute(any()) } returns Result.success(Unit)
            coEvery { LoginTestDependencies.validateUserEmailUseCase.execute(any()) } returns Result.success(Unit)
            coEvery { LoginTestDependencies.doLoginUseCase.execute(any()) } returns Result.failure(exception)

            onLaunch {
                typeAnEmail("test@gmail.com")
                typeAPassword("213521")
                clickOnEnterButton()
            }.checkIf {
                checkTextOnDialog(exception.message.orEmpty())
            }
        }
    }
}
