package com.example.idictionary.di

import android.app.Application
import org.koin.core.context.startKoin

class DictionaryApp:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            modules(listOf(applicationModule, networkModule))
        }
    }
}