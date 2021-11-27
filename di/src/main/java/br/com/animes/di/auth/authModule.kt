package br.com.animes.di.auth

import br.com.animes.base.data.remote.factory.WebServiceFactory
import br.com.animes.di.web.services.ANONYMOUS_WEB_SERVICE_FACTORY
import br.com.animes.feature.auth.data.AuthRemoteDataSource
import br.com.animes.feature.auth.data.AuthRepositoryImpl
import br.com.animes.feature.auth.data.remote.datasource.AuthRemoteDataSourceImpl
import br.com.animes.feature.auth.data.remote.service.LoginWebService
import br.com.animes.feature.auth.domain.repository.AuthRepository
import br.com.animes.feature.auth.domain.use.cases.DoLogin
import br.com.animes.feature.auth.domain.use.cases.ValidateAppPassword
import br.com.animes.feature.auth.domain.use.cases.ValidateUserEmail
import br.com.animes.feature.auth.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

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
    factory { ValidateUserEmail() }

    factory { ValidateAppPassword() }


    factory { DoLogin(get(), get(), get()) }

    //VIEW_MODEL
    viewModel {
        LoginViewModel(get(), get(), get(), get())
    }
}
