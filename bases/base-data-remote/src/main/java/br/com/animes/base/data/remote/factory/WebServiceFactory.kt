package br.com.animes.base.data.remote.factory

interface WebServiceFactory {
    fun <T> createWebService(
        service: Class<T>
    ): T
}
