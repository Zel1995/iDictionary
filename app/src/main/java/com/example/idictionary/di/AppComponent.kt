package com.example.idictionary.di

import com.example.idictionary.MainActivity
import com.example.idictionary.view.MainFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, RepositoryModule::class,ViewModelModule::class])
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        fun build(): AppComponent
    }

    fun inject(main: MainActivity)
    fun inject(main: MainFragment)
}