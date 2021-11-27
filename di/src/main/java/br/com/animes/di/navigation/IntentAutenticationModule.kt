//package br.com.animes.di.intent
//
//import androidx.fragment.app.Fragment
//import br.com.animes.feature.auth.navigation.AuthNavigation
//import br.com.animes.intent.navigation.auth.AuthNavigationImpl
//import org.koin.dsl.module
//
//val intentAuthenticationModule = module {
//    factory<AuthNavigation> { (fragment: Fragment) ->
//        AuthNavigationImpl(fragment)
//    }
//}