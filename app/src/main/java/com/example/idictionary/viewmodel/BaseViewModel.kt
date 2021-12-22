package com.example.idictionary.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.model.AppState
import com.example.idictionary.rx.SchedulerProvider

abstract class BaseViewModel<T : com.example.model.AppState>(
    protected val _mutableLiveData: MutableLiveData<T> = MutableLiveData(),
    protected val schedulerProvider: SchedulerProvider = SchedulerProvider()
):ViewModel() {
    abstract fun getData(word:String = "")
}