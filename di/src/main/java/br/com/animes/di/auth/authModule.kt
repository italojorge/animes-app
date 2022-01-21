package br.com.animes.di.auth

import br.com.animes.base.data.remote.factory.WebServiceFactory
import br.com.animes.di.web.services.ANONYMOUS_WEB_SERVICE_FACTORY
import br.com.animes.domain.core.UseCase
import br.com.animes.feature.auth.data.AuthRemoteDataSource
import br.com.animes.feature.auth.data.AuthRepositoryImpl
import br.com.animes.feature.auth.data.remote.datasource.AuthRemoteDataSourceImpl
import br.com.animes.feature.auth.data.remote.service.LoginWebService
import br.com.animes.feature.auth.domain.model.UserCredentials
import br.com.animes.feature.auth.domain.repository.AuthRepository
import br.com.animes.feature.auth.domain.use.cases.DoLoginUseCase
import br.com.animes.feature.auth.domain.use.cases.ValidateAppPasswordUseCase
import br.com.animes.feature.auth.domain.use.cases.ValidateUserEmailUseCase
import br.com.animes.feature.auth.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val VALIDATE_USER_EMAIL_USE_CASE = "VALIDATE_USER_EMAIL_USE_CASE"
const val VALIDATE_APP_PASSWORD_USE_CASE = "VALIDATE_APP_PASSWORD_USE_CASE"
const val DO_LOGIN_USE_CASE = "DO_LOGIN_USE_CASE"

val authModule = module {
    //SERVICE
    single {
        get<WebServiceFactory>(named(ANONYMOUS_WEB_SERVICE_FACTORY))
            .createWebService(LoginWebService::class.java)
    }

    //DATA_REMOTE
    single<AuthRemoteDataSource> {
        AuthRemoteDataSourceImpl(get())
    }

    //DATA
    single<AuthRepository> {
        AuthRepositoryImpl(get(), get())
    }

    //DOMAIN
    factory<UseCase<ValidateUserEmailUseCase.Params, Unit>>(named(VALIDATE_USER_EMAIL_USE_CASE)) { ValidateUserEmailUseCase() }

    factory<UseCase<ValidateAppPasswordUseCase.Params, Unit>>(named(VALIDATE_APP_PASSWORD_USE_CASE)) { ValidateAppPasswordUseCase() }

    factory<UseCase<UserCredentials, Unit>>(named(DO_LOGIN_USE_CASE)) {
        DoLoginUseCase(
            validateAppPasswordUseCase = get(named(VALIDATE_APP_PASSWORD_USE_CASE)),
            validateUserEmailUseCase = get(named(VALIDATE_USER_EMAIL_USE_CASE)), authRepository = get()
        )
    }

    //VIEW_MODEL
    viewModel {
        LoginViewModel(
            validateUserEmailUseCase = get(named(VALIDATE_USER_EMAIL_USE_CASE)),
            validateAppPasswordUseCase = get(named(VALIDATE_APP_PASSWORD_USE_CASE)),
            doLoginUseCase = get(named(DO_LOGIN_USE_CASE)),
            authRepository = get()
        )
    }
}
