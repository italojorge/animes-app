package br.com.animes.domain

interface AnalyticsEvent {
    fun recordException(throwable: Throwable)
    fun recordScreen(screenName: String)
}