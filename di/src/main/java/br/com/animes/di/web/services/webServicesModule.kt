package br.com.animes.di.web.services

import br.com.animes.base.data.remote.factory.DefaultWebServiceFactoryImpl
import br.com.animes.base.data.remote.factory.WebServiceFactory
import br.com.animes.di.DIBuildConfigValues
import br.com.animes.feature.auth.data.remote.factory.AnonymousWebServiceFactoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val ANONYMOUS_WEB_SERVICE_FACTORY = "LOGIN_WEB_SERVICE_FACTORY"

val webServicesModule = module {
    single<WebServiceFactory> {
        DefaultWebServiceFactoryImpl(
            baseUrl = DIBuildConfigValues.baseUrl,
            userSessionManager = get(),
            isDebugModeOn = DIBuildConfigValues.isDebug
        )
    }

    single<WebServiceFactory>(named(ANONYMOUS_WEB_SERVICE_FACTORY)) {
        AnonymousWebServiceFactoryImpl(
            baseUrl = DIBuildConfigValues.baseUrl,
            isDebugModeOn = DIBuildConfigValues.isDebug
        )
    }
}

