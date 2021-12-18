package com.example.idictionary.model.repository

import com.example.idictionary.model.data.AppState

interface LocalRepository<T> {
    suspend fun getData():T

    suspend fun saveToDb(appState: AppState)
}