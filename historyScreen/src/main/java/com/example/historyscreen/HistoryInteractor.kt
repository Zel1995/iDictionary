package com.example.historyscreen

import com.example.idictionary.model.repository.LocalRepository

class HistoryInteractor(
    private val localRepository: LocalRepository<com.example.model.AppState>
) {
    suspend fun getHistoryData(): com.example.model.AppState {
        return localRepository.getData()
    }
}