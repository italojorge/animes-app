package br.com.animes.di

import android.annotation.SuppressLint
import br.com.animes.di.event.AnalyticsEventImpl
import br.com.animes.domain.AnalyticsEvent
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

@SuppressLint("MissingPermission")
val domainModule = module {
    single<AnalyticsEvent> {
        AnalyticsEventImpl(
            firebaseCrashlytics = FirebaseCrashlytics.getInstance(),
            firebaseAnalytics = FirebaseAnalytics.getInstance(androidContext())
        )
    }
}