package com.example.idictionary.rx

import io.reactivex.Scheduler
import kotlinx.coroutines.CoroutineDispatcher

interface ISchedulerProvider {
    fun ui(): CoroutineDispatcher

    fun io(): CoroutineDispatcher
}