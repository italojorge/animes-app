package br.com.animes.navigation.utils

import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

fun Fragment.navigate(deepLinkRequest: NavDeepLinkRequest, navOptions: NavOptions) {
    try {
        findNavController().navigate(deepLinkRequest, navOptions)
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
    }
}
