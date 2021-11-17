package br.com.animes.data.remote.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJson(json: String): T =
    this.fromJson(json, object : TypeToken<T>() {}.type)

inline fun <reified T> Gson.fromJsonOrNull(json: String): T? {
    return try {
        this.fromJson(json, object : TypeToken<T>() {}.type)
    } catch (exception: Exception) {
        null
    }
}
