package com.example.repository

import com.example.idictionary.model.repository.LocalRepository
import com.example.idictionary.model.storage.HistoryDao
import com.example.model.AppState

class LocalRepositoryImpl(private val historyDao: HistoryDao): LocalRepository<AppState> {
    override suspend fun getData(): AppState {
            return AppState.Success(historyDao.getAll().map {
                com.example.utils.fromDbToUiData(
                    it
                )
            })
    }

    override suspend fun saveToDb(appState: AppState) {
        com.example.utils.fromUiToDbData(appState)?.let { historyDao.insert(it) }
    }
}