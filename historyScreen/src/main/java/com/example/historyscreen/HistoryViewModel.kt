package com.example.historyscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.AppState
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val interactor: HistoryInteractor,
) : ViewModel() {
    private val _mutableLiveData = MutableLiveData<AppState>()
    private val liveDataForViewToObserve: LiveData<com.example.model.AppState> = _mutableLiveData

    fun subscribe(): LiveData<com.example.model.AppState> {
        return liveDataForViewToObserve
    }

    fun getData(word: String = "") {
        viewModelScope.launch {
            val result = interactor.getHistoryData()
            val mapped = com.example.utils.parseSearchResult(result)
            _mutableLiveData.postValue(mapped)
        }
    }
}