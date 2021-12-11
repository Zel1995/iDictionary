package com.example.idictionary.di

import com.example.idictionary.model.data.DataModel
import com.example.idictionary.model.data.api.ApiService
import com.example.idictionary.model.repository.Repository
import com.example.idictionary.model.repository.RepositoryImplementation
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun providesRepository(apiService: ApiService):Repository<List<DataModel>> {
        return RepositoryImplementation(apiService)
    }
}