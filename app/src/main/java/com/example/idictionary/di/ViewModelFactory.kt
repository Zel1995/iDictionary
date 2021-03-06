package com.example.idictionary.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val provider = viewModels[modelClass] ?: throw IllegalArgumentException("unknown model class $modelClass")
        return provider.get() as T
    }
}