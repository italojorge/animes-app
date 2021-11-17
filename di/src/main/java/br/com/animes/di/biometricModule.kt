package br.com.animes.di

import androidx.biometric.BiometricManager
import androidx.fragment.app.Fragment
import br.com.animes.feature.auth.biometric.BiometricAuthenticator
import br.com.animes.feature.auth.biometric.BiometricAuthenticatorImpl
import br.com.animes.feature.auth.biometric.BiometricChecker
import br.com.animes.feature.auth.biometric.BiometricCheckerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val biometricAuthenticationModule = module {

    factory { BiometricManager.from(androidContext()) }

    factory<BiometricChecker> {
        BiometricCheckerImpl(biometricManager = get())
    }

    factory<BiometricAuthenticator> { (fragment: Fragment) ->
        BiometricAuthenticatorImpl(
            fragment = fragment,
            biometricChecker = get()
        )
    }
}
