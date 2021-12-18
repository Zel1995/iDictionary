package com.example.idictionary.model.repository

import com.example.idictionary.model.data.AppState
import com.example.idictionary.model.data.DataModel
import com.example.idictionary.model.storage.HistoryDao
import com.example.idictionary.utils.fromDbToUiData
import com.example.idictionary.utils.fromUiToDbData
import java.lang.Exception

class LocalRepositoryImpl(private val historyDao: HistoryDao):LocalRepository<AppState> {
    override suspend fun getData(): AppState {
            return AppState.Success(historyDao.getAll().map { fromDbToUiData(it) })
    }

    override suspend fun saveToDb(appState: AppState) {
        fromUiToDbData(appState)?.let { historyDao.insert(it) }
    }
}