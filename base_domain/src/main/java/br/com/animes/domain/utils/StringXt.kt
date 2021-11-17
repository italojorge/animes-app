package br.com.animes.domain.utils

fun String.isRepeating() = toCharArray().toSet().size == 1