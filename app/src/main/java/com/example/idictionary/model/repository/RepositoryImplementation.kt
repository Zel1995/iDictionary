package com.example.idictionary.model.repository

import com.example.idictionary.model.data.DataModel
import com.example.idictionary.model.data.api.ApiService
import io.reactivex.Observable

class RepositoryImplementation(private val apiService: ApiService) :
    Repository<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        return apiService.search(word)
    }
}