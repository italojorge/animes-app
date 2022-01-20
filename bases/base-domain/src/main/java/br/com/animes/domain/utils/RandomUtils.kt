package br.com.animes.domain.utils

import java.util.*

val randomString: String get() = UUID.randomUUID().toString()
val randomBoolean: Boolean get() = Random().nextBoolean()
val randomInt: Int get() = Random().nextInt()
val randomLong: Long get() = Random().nextLong()
val randomDouble: Double get() = Random().nextDouble()