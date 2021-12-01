package br.com.animes.navigation.auth

import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import br.com.animes.feature.auth.navigation.AuthNavigation
import br.com.animes.intent.R
import br.com.animes.navigation.utils.navigate

class AuthNavigationImpl(private val fragment: Fragment) : AuthNavigation {
    override fun navigateToHome() {
        val request = NavDeepLinkRequest.Builder
            .fromUri(fragment.requireContext().getString(R.string.access_home_deeplink).toUri())
            .build()
        fragment.navigate(request)
    }
}
