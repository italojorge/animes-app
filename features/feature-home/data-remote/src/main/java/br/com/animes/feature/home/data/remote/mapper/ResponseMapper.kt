package br.com.animes.feature.home.data.remote.mapper

import br.com.animes.feature.home.data.remote.model.AnimeResponse
import br.com.animes.feature.home.data.remote.model.details.AnimeDetailsResponse
import br.com.animes.feature.home.domain.model.Anime
import br.com.animes.feature.home.domain.model.AnimeDetails

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

fun AnimeDetailsResponse.toDomain() = AnimeDetails(
    id = id ?: -1L,
    rank = rank,
    title = title.orEmpty(),
    imageUrl = imageUrl.orEmpty(),
    type = type.orEmpty(),
    episodes = episodes,
    date = dateResponse?.date,
    members = members ?: -1,
    score = score ?: 0.0,
    synopsis = synopsis.orEmpty(),
    status = status,
    source = source,
    duration = duration
)