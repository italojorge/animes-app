package br.com.animes.di.bases

import br.com.animes.base.data.remote.factory.UserSessionManager
import br.com.animes.data.local.session.UserAuthLocalImpl
import br.com.animes.feature.auth.data.AuthLocalDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataLocalModule = module {
    single<UserSessionManager> {
        UserAuthLocalImpl(androidContext())
    }

    single<AuthLocalDataSource> {
        UserAuthLocalImpl(androidContext())
    }
}