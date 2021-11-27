package br.com.animes.feature.auth.domain.use.cases

import br.com.animes.domain.exception.DomainException


sealed class CredentialsException(message: String, title: String? = null) :
    DomainException(message, title)

class InvalidPasswordException : CredentialsException("Senha inválida.")
class InvalidEmailException : CredentialsException("Email inválido.")
class RepeatingPasswordException : CredentialsException("Senha inválida.")

class LoginInvalidCredentialsException :
    CredentialsException("Dados inválidos. Confira novamente as informações inseridas.")