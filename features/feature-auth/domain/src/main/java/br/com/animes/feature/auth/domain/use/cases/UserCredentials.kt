package br.com.animes.feature.auth.domain.use.cases

data class UserCredentials(
    val email: String,
    val password: String
)