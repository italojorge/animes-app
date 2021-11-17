package br.com.animes.di.event

import android.os.Bundle
import br.com.animes.domain.AnalyticsEvent
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics

class AnalyticsEventImpl(
    private val firebaseCrashlytics: FirebaseCrashlytics,
    private val firebaseAnalytics: FirebaseAnalytics
) : AnalyticsEvent {
    override fun recordException(throwable: Throwable) {
        firebaseCrashlytics.recordException(throwable)
    }

    override fun recordScreen(screenName: String) {
        val bundle = Bundle().apply {
            putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
            putString(FirebaseAnalytics.Param.SCREEN_CLASS, screenName)
        }
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }
}