package com.example.idictionary.viewmodel

import com.example.idictionary.model.data.AppState
import com.example.idictionary.model.data.DataModel
import com.example.idictionary.model.repository.Repository
import io.reactivex.Observable
import javax.inject.Inject

class MainInteractor (private val repository: Repository<List<DataModel>>) {
    suspend fun getData(word: String): AppState {
        return AppState.Success(repository.getData(word))
    }
}