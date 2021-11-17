package br.com.animes.domain.core

import br.com.animes.domain.utils.Result

abstract class UseCase<Input, Output> {
    abstract suspend fun execute(param: Input): Result<Output>
}