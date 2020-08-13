package com.nutrition

import android.app.Application
import android.os.StrictMode
import com.nutrition.di.AppModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        setUpKoin()
        setUpTimber()
        setUpStrictMode()
    }

    private fun setUpKoin() = startKoin {
        androidContext(this@MyApplication)
        modules(AppModules.modules)
    }

    private fun setUpTimber() = Timber.plant(DebugTree())

    private fun setUpStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )
        }
    }
}