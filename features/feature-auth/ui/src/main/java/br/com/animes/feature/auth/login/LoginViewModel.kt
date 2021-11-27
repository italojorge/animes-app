package br.com.animes.feature.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.animes.core.utils.livedata.SingleLiveDataEvent
import br.com.animes.core.utils.livedata.ViewState
import br.com.animes.core.utils.livedata.postFailure
import br.com.animes.core.utils.livedata.postLoading
import br.com.animes.core.utils.livedata.postSuccess
import br.com.animes.domain.utils.Result
import br.com.animes.feature.auth.domain.repository.AuthRepository
import br.com.animes.feature.auth.domain.use.cases.DoLogin
import br.com.animes.feature.auth.domain.use.cases.UserCredentials
import br.com.animes.feature.auth.domain.use.cases.ValidateAppPassword
import br.com.animes.feature.auth.domain.use.cases.ValidateUser
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val validateUserUseCase: ValidateUser,
    private val validateAppPasswordUseCase: ValidateAppPassword,
    private val doLoginUseCase: DoLogin,
    private val authRepository: AuthRepository,
    private val dispatcher: CoroutineContext = Dispatchers.Main
) : ViewModel() {
    private val _loginViewState = SingleLiveDataEvent<ViewState<Unit>>()
    private val _userError = MutableLiveData<Throwable>()
    private val _passwordError = MutableLiveData<Throwable>()
    private val _hasCredentials = MutableLiveData<Boolean>()
    private val _userEmail = SingleLiveDataEvent<String>()

    val loginViewState: LiveData<ViewState<Unit>> = _loginViewState
    val userError: LiveData<Throwable> = _userError
    val passwordError: LiveData<Throwable> = _passwordError
    val hasCredentials: LiveData<Boolean> = _hasCredentials
    val userEmail: LiveData<String> = _userEmail

    fun doLogin() {
        viewModelScope.launch(dispatcher) {
            _loginViewState.postLoading()
            authRepository.doLogin().onSuccess {
                _loginViewState.postSuccess(it)
            }.onFailure {
                _loginViewState.postFailure(it)
            }
        }
    }

    fun getUser() {
        viewModelScope.launch(dispatcher) {
            _userEmail.postValue(authRepository.getUserEmail().orEmpty())
        }
    }

    fun doLogin(user: String, password: String, saveCredentials: Boolean) {
        viewModelScope.launch(dispatcher) {
            val validateUserResult = validateUser(user)
            val validatePasswordResult = validatePassword(password)

            if (validatePasswordResult.isSuccess && validateUserResult.isSuccess) {
                _loginViewState.postLoading()
                login(user, password, saveCredentials)
            }
        }
    }

    fun checkCredentials() {
        viewModelScope.launch(dispatcher) {
            authRepository.hasCredentials().onSuccess {
                _hasCredentials.postValue(it)
            }
        }
    }

    private suspend fun login(user: String, password: String, saveCredentials: Boolean) {
        doLoginUseCase.execute(
            UserCredentials(
                user, password
            )
        ).onSuccess {
            if (saveCredentials) authRepository.saveCredentials(UserCredentials(user, password))
            _loginViewState.postSuccess(it)
        }.onFailure {
            _loginViewState.postFailure(it)
        }
    }

    private suspend fun validatePassword(password: String): Result<Unit> {
        return validateAppPasswordUseCase.execute(
            ValidateAppPassword.Params(
                password
            )
        ).onFailure {
            _passwordError.postValue(it)
        }
    }

    private suspend fun validateUser(user: String): Result<Unit> {
        return validateUserUseCase.execute(
            ValidateUser.Params(user)
        ).onFailure { error ->
            _userError.postValue(error)
        }
    }
}
