package br.com.animes.base.data.remote.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJsonOrNull(json: String): T? {
    return try {
        this.fromJson(json, object : TypeToken<T>() {}.type)
    } catch (exception: Exception) {
        null
    }
}

inline fun <reified T> Gson.fromJson(json: String): T {
    return this.fromJson(json, object : TypeToken<T>() {}.type)
}
