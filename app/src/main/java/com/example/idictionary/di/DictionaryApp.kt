package com.example.idictionary.di

import android.app.Application

class DictionaryApp:Application() {
    val component = DaggerAppComponent.builder().build()
}