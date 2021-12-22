package com.example.idictionary.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.utils.parseSearchResult
import com.example.idictionary.viewmodel.BaseViewModel
import com.example.idictionary.viewmodel.MainInteractor
import kotlinx.coroutines.launch


class MainViewModel(private val interactor: MainInteractor) :
    BaseViewModel<com.example.model.AppState>() {
    private var appState: com.example.model.AppState? = null
    private val liveDataForViewToObserve:LiveData<com.example.model.AppState> = _mutableLiveData
    fun subscribe(): LiveData<com.example.model.AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String) {
        viewModelScope.launch{
            val result = interactor.getData(word)
            val mapped = com.example.utils.parseSearchResult(result)
            _mutableLiveData.postValue(mapped)
        }

    }
}