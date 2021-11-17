package br.com.animes.app.modules

import br.com.animes.di.authModule
import br.com.animes.di.biometricAuthenticationModule
import br.com.animes.di.dataModule
import br.com.animes.di.domainModule
import br.com.animes.di.intent.intentAuthenticationModule

val moduleList = listOf(
    dataModule,
    authModule,
    domainModule,
    intentAuthenticationModule,
    biometricAuthenticationModule
)