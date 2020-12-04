package com.beshoy.nytimes

import androidx.multidex.MultiDexApplication
import com.beshoy.nytimes.koin.homeModule
import com.beshoy.nytimes.koin.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class NyTimesApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        startKoin {
            androidContext(this@NyTimesApp)
            modules(koinModules)
        }
    }

    companion object {
        val koinModules = listOf(networkModule, homeModule)
    }
}