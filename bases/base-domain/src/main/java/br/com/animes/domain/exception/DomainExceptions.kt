package br.com.animes.domain.exception

open class DomainException(message: String, title: String? = null) :
    RuntimeException(message, RuntimeException(title))

sealed class ParamException(message: String, title: String? = null) :
    DomainException(message, title)

class MissingParamsException : ParamException("Os parâmetros não podem ser nulos")
class EmptyFieldException : ParamException("Campo obrigatório.")
class FieldValueException(message: String) : ParamException(message)