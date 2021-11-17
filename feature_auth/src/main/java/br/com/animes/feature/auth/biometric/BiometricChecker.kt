package br.com.animes.feature.auth.biometric

interface BiometricChecker {
    fun hasBiometrics(): Boolean
}