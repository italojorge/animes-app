package br.com.animes.domain.utils

import java.util.regex.Pattern

fun String.isRepeating() = toCharArray().toSet().size == 1

fun String.isNotEmail() = EMAIL_ADDRESS.matcher(this).matches().not()

private val EMAIL_ADDRESS: Pattern = Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
)