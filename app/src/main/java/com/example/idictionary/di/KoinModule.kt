package com.example.idictionary.di

import com.example.idictionary.model.data.api.BaseInterceptor
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import com.example.idictionary.BuildConfig
import com.example.idictionary.model.data.DataModel
import com.example.idictionary.model.data.api.ApiService
import com.example.idictionary.model.repository.Repository
import com.example.idictionary.model.repository.RepositoryImplementation
import com.example.idictionary.view.MainViewModel
import com.example.idictionary.viewmodel.MainInteractor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory {
        OkHttpClient.Builder()
            .addInterceptor(BaseInterceptor.interceptor)
            .build()
    }
    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    single { get<Retrofit>().create(ApiService::class.java) }
}
val applicationModule = module {
    single <Repository<List<DataModel>>>{ RepositoryImplementation( apiService = get()) }
    single { MainInteractor(repository = get()) }
    viewModel { MainViewModel(interactor = get()) }
}