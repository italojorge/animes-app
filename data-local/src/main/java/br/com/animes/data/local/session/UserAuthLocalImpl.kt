package br.com.animes.data.local.session

import android.content.Context
import androidx.core.content.edit
import br.com.animes.base.data.remote.factory.UserSessionManager
import br.com.animes.base.data.remote.utils.fromJsonOrNull
import br.com.animes.domain.utils.Result
import br.com.animes.feature.auth.data.AuthLocalDataSource
import br.com.animes.feature.auth.domain.model.UserCredentials
import com.google.gson.Gson

class UserAuthLocalImpl(context: Context) : UserSessionManager, AuthLocalDataSource {
    private companion object {
        const val SESSION_DATA_KEY: String = "br.com.animes.data.local.model.SessionData"
        const val SESSION_STORE_KEY: String =
            "br.com.animes.data.local.datasource.SessionDataSource"
        const val USER_CREDENTIALS_KEY: String = "user-credentials"
        const val USER_EMAIL_KEY: String = "user-email"
    }

    private val preferences = context.getSharedPreferences(SESSION_STORE_KEY, Context.MODE_PRIVATE)

    override fun saveSession(sessionData: String) {
        preferences.edit {
            putString(SESSION_DATA_KEY, sessionData)
        }
    }

    override fun saveCredentials(userCredentials: UserCredentials): Result<Unit> {
        val result = preferences.edit {
            putString(USER_CREDENTIALS_KEY, Gson().toJson(userCredentials))
        }
        return Result.success(result)
    }

    override fun saveUserEmail(username: String) {
        preferences.edit {
            putString(USER_EMAIL_KEY, username)
        }
    }

    override fun getUserEmail(): String? {
        return preferences.getString(USER_EMAIL_KEY, "")
    }

    override fun getSession(): String? {
        return preferences.getString(SESSION_DATA_KEY, "")
    }

    override fun getCredentials(): Result<UserCredentials?> {
        val userCredentials =
            preferences.getString(USER_CREDENTIALS_KEY, null) ?: return Result.success(null)
        return Result.success(Gson().fromJsonOrNull(userCredentials))
    }

    override fun hasCredentials(): Result<Boolean> {
        return Result.success(getCredentials().getOrNull() != null)
    }
}