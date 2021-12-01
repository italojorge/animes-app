package br.com.animes.navigation.utils

import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController

fun Fragment.navigate(deepLinkRequest: NavDeepLinkRequest) {
    try {
        findNavController().navigate(deepLinkRequest)
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
    }
}
