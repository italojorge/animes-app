package br.com.animes.feature.auth.biometric

interface BiometricAuthenticator {
    fun authenticate(callback: (AuthenticationResult) -> Unit)
}