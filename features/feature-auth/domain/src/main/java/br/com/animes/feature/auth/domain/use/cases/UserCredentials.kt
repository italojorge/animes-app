package br.com.animes.feature.auth.domain.use.cases

data class UserCredentials(
    val user: String,
    val password: String
)