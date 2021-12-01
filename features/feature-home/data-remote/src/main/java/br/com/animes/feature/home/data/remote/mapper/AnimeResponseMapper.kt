package br.com.animes.feature.home.data.remote.mapper

import br.com.animes.feature.home.data.remote.model.AnimeResponse
import br.com.animes.feature.home.domain.model.Anime

fun AnimeResponse.toDomain() = Anime(
    id = id ?: -1L,
    rank = rank ?: -1,
    title = title.orEmpty(),
    imageUrl = imageUrl.orEmpty(),
    type = type.orEmpty(),
    episodes = episodes,
    startDate = startDate,
    endDate = endDate,
    members = members ?: -1,
    score = score ?: 0.0
)