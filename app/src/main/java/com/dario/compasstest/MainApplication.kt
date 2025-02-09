package com.dario.compasstest

import android.app.Application
import com.dario.compasstest.di.plainTextAnalyzeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)
            modules(
                listOf(plainTextAnalyzeModule)
            )
        }
    }
}