package br.com.animes.feature.auth.model

import androidx.annotation.StringRes
import br.com.animes.domain.exception.EmptyFieldException
import br.com.animes.feature.auth.R
import br.com.animes.feature.auth.domain.use.cases.InvalidEmailException
import br.com.animes.feature.auth.domain.use.cases.InvalidPasswordException
import br.com.animes.feature.auth.domain.use.cases.LoginInvalidCredentialsException

enum class LoginErrorMessageEnum(@StringRes val value: Int?) {
    EMPTY_FIELD(R.string.login_empty_field_error_message), INVALID_PASSWORD(R.string.login_invalid_password_error_message),
    INVALID_EMAIL(R.string.login_invalid_email_error_message), INVALID_CREDENTIALS(R.string.login_invalid_credentials_error_message),
    UNKNOWN_ERROR(null);

    companion object {
        fun valueOfOrDefault(throwable: Throwable): LoginErrorMessageEnum = when (throwable) {
            is EmptyFieldException -> EMPTY_FIELD
            is LoginInvalidCredentialsException -> INVALID_CREDENTIALS
            is InvalidPasswordException -> INVALID_PASSWORD
            is InvalidEmailException -> INVALID_EMAIL
            else -> UNKNOWN_ERROR
        }
    }
}
