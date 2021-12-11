package com.example.idictionary.model.data.api

import com.example.idictionary.model.data.DataModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("words/search")
    suspend fun search(@Query("search") wordToSearch: String): List<DataModel>
}