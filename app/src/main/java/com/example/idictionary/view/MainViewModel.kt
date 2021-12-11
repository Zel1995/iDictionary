package com.example.idictionary.view

import androidx.lifecycle.LiveData
import com.example.idictionary.model.data.AppState
import com.example.idictionary.utils.parseSearchResult
import com.example.idictionary.viewmodel.BaseViewModel
import com.example.idictionary.viewmodel.MainInteractor
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

class MainViewModel (private val interactor: MainInteractor) :
    BaseViewModel<AppState>() {
    private var appState: AppState? = null

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String) {
        compositeDisposable +=
            interactor.getData(word)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { liveDataForViewToObserve.value = AppState.Loading(true) }
                .subscribe({
                    appState = parseSearchResult(it)
                    liveDataForViewToObserve.value = appState
                }, {
                    liveDataForViewToObserve.value = AppState.Error(it)
                },{
                    liveDataForViewToObserve.value = AppState.Loading(false)
                })
    }
}