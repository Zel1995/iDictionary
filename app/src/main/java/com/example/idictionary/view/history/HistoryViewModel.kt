package com.example.idictionary.view.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.idictionary.model.data.AppState
import com.example.idictionary.utils.parseSearchResult
import com.example.idictionary.viewmodel.BaseViewModel
import com.example.idictionary.viewmodel.HistoryInteractor
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val interactor: HistoryInteractor,
) : BaseViewModel<AppState>() {
    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String) {
        viewModelScope.launch {
            val result = interactor.getHistoryData()
            val mapped = parseSearchResult(result)
            _mutableLiveData.postValue(mapped)
        }
    }
}