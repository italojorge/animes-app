package br.com.animes.intent.navigation.auth

import androidx.fragment.app.Fragment
import br.com.animes.feature.auth.login.LoginFragmentDirections
import br.com.animes.feature.auth.navigation.AuthNavigation
import br.com.animes.intent.utils.navigate

class AuthNavigationImpl(private val fragment: Fragment) : AuthNavigation {
    override fun navigateToChangePassword() {
        fragment.navigate(
            LoginFragmentDirections.actionLoginFragmentToChangePasswordFragment()
        )
    }
}
