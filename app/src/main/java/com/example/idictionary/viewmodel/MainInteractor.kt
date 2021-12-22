package com.example.idictionary.viewmodel

import com.example.idictionary.model.repository.LocalRepository
import com.example.model.DataModel
import com.example.repository.Repository

class MainInteractor(
    private val repository: Repository<List<DataModel>>,
    private val localRepository: LocalRepository<List<com.example.model.DataModel>>
) {
    suspend fun getData(word: String): com.example.model.AppState {
        val result = com.example.model.AppState.Success(repository.getData(word))
        localRepository.saveToDb(result)
        return result
    }
}