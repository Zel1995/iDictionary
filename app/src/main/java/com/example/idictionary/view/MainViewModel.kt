package com.example.idictionary.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.idictionary.model.data.AppState
import com.example.idictionary.utils.parseSearchResult
import com.example.idictionary.viewmodel.BaseViewModel
import com.example.idictionary.viewmodel.MainInteractor
import kotlinx.coroutines.launch


class MainViewModel(private val interactor: MainInteractor) :
    BaseViewModel<AppState>() {
    private var appState: AppState? = null
    private val liveDataForViewToObserve:LiveData<AppState> = _mutableLiveData
    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String) {
        viewModelScope.launch{
            val result = interactor.getData(word)
            val mapped = parseSearchResult(result)
            _mutableLiveData.postValue(mapped)
        }

    }
}