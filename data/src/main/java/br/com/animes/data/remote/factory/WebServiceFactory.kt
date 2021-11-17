package br.com.animes.data.remote.factory

interface WebServiceFactory {
    fun <T> createWebService(
        service: Class<T>
    ): T
}
