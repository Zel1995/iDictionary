package com.example.idictionary.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.idictionary.model.data.AppState
import com.example.idictionary.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<T : AppState>(
    protected val _mutableLiveData: MutableLiveData<T> = MutableLiveData(),
    protected val schedulerProvider: SchedulerProvider = SchedulerProvider()
):ViewModel() {
    abstract fun getData(word:String)
}