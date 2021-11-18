package br.com.animes.app

import android.app.Application
//import br.com.animes.app.modules.moduleList
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
//            modules(moduleList)
        }
    }
}