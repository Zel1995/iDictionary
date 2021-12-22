package com.example.idictionary.model.repository

import com.example.model.DataModel
import com.example.idictionary.model.data.api.ApiService
import com.example.repository.Repository

class RepositoryImplementation(private val apiService: ApiService) :
    Repository<List<DataModel>> {
    override suspend fun getData(word: String): List<com.example.model.DataModel> {
        return apiService.search(word)
    }
}