package br.com.animes.feature.auth.biometric

import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.ERROR_USER_CANCELED
import androidx.fragment.app.Fragment
import br.com.animes.feature.auth.R
import br.com.animes.feature.auth.biometric.AuthenticationResult.*

class BiometricAuthenticatorImpl(
    private val fragment: Fragment,
    private val biometricChecker: BiometricChecker,
) : BiometricAuthenticator {
    private var callback: (AuthenticationResult) -> Unit = {}

    private val promptInfo by lazy { createBiometricPrompt() }
    private val biometricPrompt by lazy {
        BiometricPrompt(
            fragment,
            authenticationCallback
        )
    }

    private val authenticationCallback = object : BiometricPrompt.AuthenticationCallback() {
        override fun onAuthenticationError(
            errorCode: Int,
            errString: CharSequence
        ) {
            super.onAuthenticationError(errorCode, errString)
            when (errorCode) {
                ERROR_USER_CANCELED -> callback(CANCELED_BY_USER)
                else -> callback(FATAL_ERROR)
            }
        }

        override fun onAuthenticationSucceeded(
            result: BiometricPrompt.AuthenticationResult
        ) {
            super.onAuthenticationSucceeded(result)
            callback(SUCCESS)
        }

        override fun onAuthenticationFailed() {
            super.onAuthenticationFailed()
            callback(NOT_RECOGNIZED)
        }
    }

    private fun createBiometricPrompt(): BiometricPrompt.PromptInfo {
        return BiometricPrompt.PromptInfo.Builder()
            .setTitle(fragment.getString(R.string.biometric_dialog_title))
            .setNegativeButtonText(fragment.getString(R.string.biometric_dialog_negative_text))
            .build()
    }

    override fun authenticate(callback: (AuthenticationResult) -> Unit) {
        this.callback = callback
        if (biometricChecker.hasBiometrics()) {
            biometricPrompt.authenticate(promptInfo)
        } else {
            callback(UNSUPPORTED)
        }
    }
}
