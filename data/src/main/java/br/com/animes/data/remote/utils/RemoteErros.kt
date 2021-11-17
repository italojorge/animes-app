package br.com.animes.data.remote.utils

import retrofit2.HttpException

enum class ErrorMessageEnum(val value: String) {
    INTERNET_ERROR("Sem conexão com a Internet."),
    DEFAULT_ERROR("Tivemos um problema com nossos servidores, tente novamente mais tarde."),
    DEFAULT_ERROR_WITH_CODE(DEFAULT_ERROR.value + " Código de erro: ")
}

class DataSourceException(message: String = ErrorMessageEnum.DEFAULT_ERROR.value) :
    Exception(message)

class ServerHttpException(
    val errorCode: String,
    val httpException: HttpException,
    message: String = ErrorMessageEnum.DEFAULT_ERROR.value
) : Exception(message)

class ServerError(message: String = ErrorMessageEnum.DEFAULT_ERROR.value) : Exception(message)

