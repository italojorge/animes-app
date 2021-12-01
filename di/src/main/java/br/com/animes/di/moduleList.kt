package br.com.animes.di

import br.com.animes.di.auth.authModule
import br.com.animes.di.bases.dataLocalModule
import br.com.animes.di.navigation.navigationAuthModule
import br.com.animes.di.web.services.webServicesModule

val moduleList = listOf(
    webServicesModule,
    dataLocalModule,
    authModule,
    biometricAuthenticationModule,
    navigationAuthModule
)