package br.com.animes.domain.interactor.credentials

import br.com.animes.domain.exception.DomainException


sealed class CredentialsException(message: String, title: String? = null) :
    DomainException(message, title)

class InvalidPasswordException : CredentialsException("Senha inválida.")
class RepeatingPasswordException : CredentialsException("Senha inválida.")
class PasswordNotMatchException : CredentialsException("Os campos diferem.")

class LoginInvalidCredentialsException :
    CredentialsException("Dados inválidos. Confira novamente as informações inseridas.")
class ChangePasswordInvalidCredentialsException :
    CredentialsException("Dados inválidos. Confira novamente as informações inseridas.")