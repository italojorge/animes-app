package br.com.animes.feature.auth.biometric

enum class AuthenticationResult {
    /**
     * A biometric (e.g. fingerprint, face, etc.) is recognized, indicating that the
     * user has successfully authenticated.
     */
    SUCCESS,

    /**
     * An unrecoverable error has been encountered and authentication has stopped.
     */
    FATAL_ERROR,

    /**
     * The user canceled the operation
     */
    CANCELED_BY_USER,

    /**
     * A biometric (e.g. fingerprint, face, etc.) is presented but not recognized as
     * belonging to the user.
     */
    NOT_RECOGNIZED,

    /**
     * The user can't authenticate using the biometric sensor because the result of
     * BiometricManager.canAuthenticate wasn't BIOMETRIC_SUCCESS
     */
    UNSUPPORTED
}
