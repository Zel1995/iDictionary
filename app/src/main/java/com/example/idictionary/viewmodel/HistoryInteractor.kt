package com.example.idictionary.viewmodel

import com.example.idictionary.model.data.AppState
import com.example.idictionary.model.data.DataModel
import com.example.idictionary.model.repository.LocalRepository

class HistoryInteractor(
    private val localRepository: LocalRepository<AppState>
) {
    suspend fun getHistoryData():AppState{
        return localRepository.getData()
    }
}