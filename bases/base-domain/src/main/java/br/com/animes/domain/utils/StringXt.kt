package br.com.animes.domain.utils

import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

fun String.isRepeating() = toCharArray().toSet().size == 1

fun String.isNotEmail() = EMAIL_ADDRESS.matcher(this).matches().not()

fun String.formatToAppDateOrNull(): String? {
    return try {
        val inputDateFormat = SimpleDateFormat(INPUT_DATE_FORMAT, Locale.getDefault())
        val appDateFormat = SimpleDateFormat(APP_DATE_FORMAT, Locale.getDefault())
        appDateFormat.format(inputDateFormat.parse(this))
    } catch (e: Exception) {
        null
    }
}

private const val INPUT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
private const val APP_DATE_FORMAT = "MMM yyyy"
private val EMAIL_ADDRESS: Pattern = Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
)