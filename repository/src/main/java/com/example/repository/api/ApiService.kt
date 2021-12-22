package com.example.idictionary.model.data.api

import com.example.model.DataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("words/search")
    suspend fun search(@Query("search") wordToSearch: String): List<com.example.model.DataModel>
}