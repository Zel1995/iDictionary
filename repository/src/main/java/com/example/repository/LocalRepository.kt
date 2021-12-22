package com.example.idictionary.model.repository

import com.example.model.AppState

interface LocalRepository<T> {
    suspend fun getData():T

    suspend fun saveToDb(appState: com.example.model.AppState)
}