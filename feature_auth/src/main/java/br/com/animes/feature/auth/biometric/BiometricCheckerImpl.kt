package br.com.animes.feature.auth.biometric

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.BIOMETRIC_STATUS_UNKNOWN
import androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS

class BiometricCheckerImpl(
    private val biometricManager: BiometricManager
): BiometricChecker {

    override fun hasBiometrics(): Boolean {
        val canAuth = biometricManager.canAuthenticate(BIOMETRIC_STRONG)
        return canAuth == BIOMETRIC_SUCCESS || canAuth == BIOMETRIC_STATUS_UNKNOWN
    }
}
