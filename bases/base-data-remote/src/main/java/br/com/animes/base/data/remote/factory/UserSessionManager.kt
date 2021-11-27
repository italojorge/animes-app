package br.com.animes.base.data.remote.factory

interface UserSessionManager {
    fun getSession(): String?
}