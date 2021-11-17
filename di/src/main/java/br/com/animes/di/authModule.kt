package br.com.animes.di

import br.com.animes.data.local.datasource.SessionDataSource
import br.com.animes.data.local.datasource.SessionDataSourceImpl
import br.com.animes.data.remote.datasource.AuthRemoteDataSource
import br.com.animes.data.remote.datasource.AuthRemoteDataSourceImpl
import br.com.animes.data.remote.factory.WebServiceFactory
import br.com.animes.data.remote.service.LoginWebService
import br.com.animes.data.repository.AuthRepositoryImpl
import br.com.animes.domain.interactor.credentials.DoLogin
import br.com.animes.domain.interactor.credentials.ValidateAppPassword
import br.com.animes.domain.interactor.credentials.ValidateUser
import br.com.animes.domain.repository.AuthRepository
import br.com.animes.feature.auth.login.LoginViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val authModule = module {
    //SERVICE
    single {
        get<WebServiceFactory>(named(LOGIN_WEB_SERVICE_FACTORY))
            .createWebService(LoginWebService::class.java)
    }

    //DATA
    single<AuthRepository> {
        AuthRepositoryImpl(get(), get())
    }

    single<SessionDataSource> {
        SessionDataSourceImpl(
            androidApplication()
        )
    }

    single<AuthRemoteDataSource> {
        AuthRemoteDataSourceImpl(get())
    }

    //DOMAIN
    factory { ValidateUser() }

    factory { ValidateAppPassword() }


    factory { DoLogin(get(), get(), get()) }

    //VIEWMODEL
    viewModel {
        LoginViewModel(get(), get(), get(), get())
    }
}
