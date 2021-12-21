package com.example.idictionary.di

import androidx.room.Room
import com.example.historyscreen.HistoryFragment
import com.example.historyscreen.HistoryInteractor
import com.example.historyscreen.HistoryViewModel
import com.example.idictionary.BuildConfig
import com.example.idictionary.model.data.api.ApiService
import com.example.idictionary.model.data.api.BaseInterceptor
import com.example.idictionary.model.repository.LocalRepository
import com.example.idictionary.model.repository.RepositoryImplementation
import com.example.idictionary.model.storage.HistoryDataBase
import com.example.idictionary.view.main.MainFragment
import com.example.idictionary.view.main.MainViewModel
import com.example.idictionary.viewmodel.MainInteractor
import com.example.model.DataModel
import com.example.repository.LocalRepositoryImpl
import com.example.repository.Repository
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
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
    single<LocalRepository<com.example.model.AppState>> { LocalRepositoryImpl(get()) }
}
val applicationModule = module {
    single<Repository<List<DataModel>>> { RepositoryImplementation(apiService = get()) }
}
val historyScreen = module {
    scope(named<HistoryFragment>()) {
        scoped { HistoryInteractor(localRepository = get()) }
        viewModel { HistoryViewModel(interactor = get()) }
    }

}
val mainScreen = module {
    scope(named<MainFragment>()) {
        viewModel { MainViewModel(interactor = get()) }
        scoped { MainInteractor(repository = get(), localRepository = get()) }
    }
}