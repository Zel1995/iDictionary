package com.example.idictionary.viewmodel

import com.example.idictionary.model.data.AppState
import com.example.idictionary.model.data.DataModel
import com.example.idictionary.model.repository.LocalRepository
import com.example.idictionary.model.repository.Repository

class MainInteractor(
    private val repository: Repository<List<DataModel>>,
    private val localRepository: LocalRepository<List<DataModel>>
) {
    suspend fun getData(word: String): AppState {
        val result = AppState.Success(repository.getData(word))
        localRepository.saveToDb(result)
        return result
    }
}