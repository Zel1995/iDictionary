package com.example.idictionary.viewmodel

import com.example.idictionary.model.data.AppState
import com.example.idictionary.model.data.DataModel
import com.example.idictionary.model.repository.Repository
import io.reactivex.Observable
import javax.inject.Inject

class MainInteractor @Inject constructor(private val repository: Repository<List<DataModel>>) {
    fun getData(word: String): Observable<AppState> {
        return repository.getData(word).map { AppState.Success(it) }
    }
}