package br.com.animes.feature.auth.login.di

import br.com.animes.domain.core.UseCase
import br.com.animes.feature.auth.biometric.BiometricAuthenticator
import br.com.animes.feature.auth.biometric.BiometricChecker
import br.com.animes.feature.auth.domain.model.UserCredentials
import br.com.animes.feature.auth.domain.repository.AuthRepository
import br.com.animes.feature.auth.domain.use.cases.ValidateAppPasswordUseCase
import br.com.animes.feature.auth.domain.use.cases.ValidateUserEmailUseCase
import br.com.animes.feature.auth.login.LoginViewModel
import br.com.animes.feature.auth.navigation.AuthNavigation
import io.mockk.mockk
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object LoginTestDependencies {
    private const val VALIDATE_USER_EMAIL_USE_CASE = "VALIDATE_USER_EMAIL_USE_CASE"
    private const val VALIDATE_APP_PASSWORD_USE_CASE = "VALIDATE_APP_PASSWORD_USE_CASE"
    private const val DO_LOGIN_USE_CASE = "DO_LOGIN_USE_CASE"

    val validateUserEmailUseCase: UseCase<ValidateUserEmailUseCase.Params, Unit> = mockk()
    val validateAppPasswordUseCase: UseCase<ValidateAppPasswordUseCase.Params, Unit> = mockk()
    val doLoginUseCase: UseCase<UserCredentials, Unit> = mockk()
    val authRepository: AuthRepository = mockk()
    val authNavigation: AuthNavigation = mockk()
    val biometricAuth: BiometricAuthenticator = mockk()
    val biometricChecker: BiometricChecker = mockk()

    val testModules = module {
        factory(named(VALIDATE_USER_EMAIL_USE_CASE)) { validateUserEmailUseCase }

        factory(named(VALIDATE_APP_PASSWORD_USE_CASE)) { validateAppPasswordUseCase }

        factory(named(DO_LOGIN_USE_CASE)) {
            doLoginUseCase
        }

        single { authRepository }
        single { authNavigation }
        single { biometricAuth }
        single { biometricChecker }

        viewModel {
            LoginViewModel(
                validateUserEmailUseCase = get(named(VALIDATE_USER_EMAIL_USE_CASE)),
                validateAppPasswordUseCase = get(named(VALIDATE_APP_PASSWORD_USE_CASE)),
                doLoginUseCase = get(named(DO_LOGIN_USE_CASE)),
                authRepository = get()
            )
        }
    }
}



