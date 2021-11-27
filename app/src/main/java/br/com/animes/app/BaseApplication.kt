package br.com.animes.app

import android.app.Application
import br.com.animes.BuildConfig
import br.com.animes.di.BuildConfigValues
import br.com.animes.di.moduleList
//import br.com.animes.di.getModuleList
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupDIBuildConfigValues()
        startKoin {
            androidContext(applicationContext)
            modules(moduleList)
        }
    }

    private fun setupDIBuildConfigValues() {
        BuildConfigValues.baseUrl = BuildConfig.BASE_URL
        BuildConfigValues.isDebug = BuildConfig.DEBUG
    }
}