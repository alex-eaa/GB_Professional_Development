package com.elchaninov.gbprofessionaldevelopment

import android.app.Application
import com.elchaninov.gbprofessionaldevelopment.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext ( this@App )
            modules(listOf(application, mainScreen, historyScreen, descriptionScreen, favoriteScreen))
        }
    }
}