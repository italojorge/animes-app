package br.com.animes.feature.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import br.com.animes.core.bases.BaseFragment
import br.com.animes.core.dialog.AlertDialogExtension.showErrorAlert
import br.com.animes.core.utils.extensions.cleanErrorTextAfterTextChanged
import br.com.animes.core.utils.navigation.navDirections
import br.com.animes.core.utils.viewbinding.viewBinding
import br.com.animes.feature.auth.biometric.AuthenticationResult
import br.com.animes.feature.auth.biometric.BiometricAuthenticator
import br.com.animes.feature.auth.biometric.BiometricChecker
import br.com.animes.feature.auth.databinding.FragmentLoginBinding
import br.com.animes.feature.auth.navigation.AuthNavigation
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class LoginFragment : BaseFragment() {
    private var binding: FragmentLoginBinding by viewBinding()
    private val navigation: AuthNavigation by navDirections()
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
        binding.loginPasswordTextInputLayout.cleanErrorTextAfterTextChanged()

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
                    Toast.makeText(requireContext(), "Sucesso", Toast.LENGTH_LONG).show()
                },
                onFailure = { showErrorAlert(it) },
                isLoading = {
                    binding.loginLoadingButton.isLoading = it
                }
            )
        }
        viewModel.passwordError.observe(owner) {
            binding.loginPasswordTextInputLayout.error = it.message
        }
        viewModel.userEmailError.observe(owner) {
            binding.loginEmailTextInputLayout.error = it.message
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