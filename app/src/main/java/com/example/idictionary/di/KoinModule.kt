package com.example.idictionary.di

import androidx.room.Room
import com.example.idictionary.BuildConfig
import com.example.idictionary.model.data.AppState
import com.example.idictionary.model.data.DataModel
import com.example.idictionary.model.data.api.ApiService
import com.example.idictionary.model.data.api.BaseInterceptor
import com.example.idictionary.model.repository.LocalRepository
import com.example.idictionary.model.repository.LocalRepositoryImpl
import com.example.idictionary.model.repository.Repository
import com.example.idictionary.model.repository.RepositoryImplementation
import com.example.idictionary.model.storage.HistoryDataBase
import com.example.idictionary.view.history.HistoryViewModel
import com.example.idictionary.view.main.MainViewModel
import com.example.idictionary.viewmodel.HistoryInteractor
import com.example.idictionary.viewmodel.MainInteractor
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
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
val storageModule = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<LocalRepository<AppState>> { LocalRepositoryImpl(get()) }
}
val applicationModule = module {
    single<Repository<List<DataModel>>> { RepositoryImplementation(apiService = get()) }
    single { MainInteractor(repository = get(), localRepository = get()) }
    single { HistoryInteractor(localRepository = get()) }
    viewModel {HistoryViewModel(interactor = get())}
    viewModel { MainViewModel(interactor = get()) }

}