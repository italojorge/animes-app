package br.com.animes.di

import br.com.animes.data.remote.factory.DefaultWebServiceFactoryImpl
import br.com.animes.data.remote.factory.LoginWebServiceFactoryImpl
import br.com.animes.data.remote.factory.WebServiceFactory
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val LOGIN_WEB_SERVICE_FACTORY = "LOGIN_WEB_SERVICE_FACTORY"

val dataModule = module {
    single<WebServiceFactory> {
        DefaultWebServiceFactoryImpl(
            baseUrl = BuildConfig.DOMAIN,
            sessionDataSource = get(),
            isDebugModeOn = BuildConfig.DEBUG
        )
    }

    single<WebServiceFactory>(named(LOGIN_WEB_SERVICE_FACTORY)) {
        LoginWebServiceFactoryImpl(
            baseUrl = BuildConfig.DOMAIN,
            isDebugModeOn = BuildConfig.DEBUG
        )
    }
}

