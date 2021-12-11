package com.example.idictionary.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.idictionary.view.MainViewModel
import com.example.idictionary.viewmodel.MainInteractor
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ViewModelModule {
    @Provides
    fun providesViewModelFactory(factory: ViewModelFactory):ViewModelProvider.Factory = factory
    @Provides
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun mainViewModel(mainInteractor: MainInteractor): ViewModel = MainViewModel(mainInteractor)
}