package br.com.animes.di.navigation

import androidx.fragment.app.Fragment
import br.com.animes.feature.auth.navigation.AuthNavigation
import br.com.animes.navigation.auth.AuthNavigationImpl
import org.koin.dsl.module

val navigationAuthModule = module {
    factory<AuthNavigation> { (fragment: Fragment) ->
        AuthNavigationImpl(fragment)
    }
}