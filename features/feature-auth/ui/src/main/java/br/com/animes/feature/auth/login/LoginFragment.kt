package br.com.animes.feature.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import br.com.animes.core.dialog.AlertDialogExtension.showAlert
import br.com.animes.core.dialog.AlertDialogExtension.showErrorAlert
import br.com.animes.core.extensions.cleanErrorTextAfterTextChanged
import br.com.animes.core.extensions.doOnSubmit
import br.com.animes.core.extensions.hideKeyboard
import br.com.animes.core.utils.navigation.navDirections
import br.com.animes.core.utils.viewbinding.viewBinding
import br.com.animes.feature.auth.biometric.AuthenticationResult
import br.com.animes.feature.auth.biometric.BiometricAuthenticator
import br.com.animes.feature.auth.biometric.BiometricChecker
import br.com.animes.feature.auth.databinding.FragmentLoginBinding
import br.com.animes.feature.auth.model.LoginErrorMessageEnum
import br.com.animes.feature.auth.model.LoginErrorMessageEnum.INVALID_CREDENTIALS
import br.com.animes.feature.auth.navigation.AuthNavigation
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class LoginFragment : Fragment() {
    private var binding: FragmentLoginBinding by viewBinding()
    private val authNavigation: AuthNavigation by navDirections()
    private val viewModel: LoginViewModel by viewModel()

    private val biometricAuth: BiometricAuthenticator by inject { parametersOf(this) }
    private val biometricChecker: BiometricChecker by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        addObservers(viewLifecycleOwner)
        viewModel.getUser()
        viewModel.checkCredentials()
    }

    private fun setupView() {
        binding.loginEmailTextInputLayout.cleanErrorTextAfterTextChanged()
        binding.loginPasswordTextInputLayout.apply {
            cleanErrorTextAfterTextChanged()
            editText?.doOnSubmit {
                binding.loginLoadingButton.performClick()
                hideKeyboard()
            }
        }

        binding.loginLoadingButton.setOnClickListener {
            val userEditText = binding.loginEmailTextInputLayout.editText
            val passwordEditText = binding.loginPasswordTextInputLayout.editText
            viewModel.doLogin(
                user = userEditText?.text?.toString().orEmpty(),
                password = passwordEditText?.text?.toString().orEmpty(),
                saveCredentials = binding.loginBiometricSwitch.isChecked
            )
        }
    }

    private fun addObservers(owner: LifecycleOwner) {
        viewModel.loginViewState.observe(owner) { viewState ->
            viewState.handleIt(
                onSuccess = {
                    authNavigation.navigateToHome()
                },
                onFailure = {
                    if (LoginErrorMessageEnum.valueOfOrDefault(it) == INVALID_CREDENTIALS)
                        INVALID_CREDENTIALS.value?.let { requireContext().showAlert(getString(INVALID_CREDENTIALS.value)) }
                    else showErrorAlert(it)
                },
                isLoading = {
                    binding.loginLoadingButton.isLoading = it
                }
            )
        }
        viewModel.passwordError.observe(owner) { errorMessageEnum ->
            errorMessageEnum.value?.let { resId ->
                binding.loginPasswordTextInputLayout.error = getString(resId)
            }
        }
        viewModel.userEmailError.observe(owner) { errorMessageEnum ->
            errorMessageEnum.value?.let { resId ->
                binding.loginEmailTextInputLayout.error = getString(resId)
            }
        }
        viewModel.hasCredentials.observe(owner) { hasCredentials ->
            binding.loginBiometricSwitch.isVisible =
                biometricChecker.hasBiometrics() && !hasCredentials
            if (hasCredentials) biometricAuth.authenticate(::handleBiometricAuthResult)
        }
        viewModel.userEmail.observe(owner) {
            binding.loginEmailTextInputLayout.editText?.setText(it)
        }
    }

    private fun handleBiometricAuthResult(
        authenticationResult: AuthenticationResult
    ) {
        when (authenticationResult) {
            AuthenticationResult.SUCCESS -> {
                viewModel.doLogin()
            }
            else -> {}
        }
    }
}