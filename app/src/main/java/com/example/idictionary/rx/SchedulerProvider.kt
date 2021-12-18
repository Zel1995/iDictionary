package com.example.idictionary.rx

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class SchedulerProvider : ISchedulerProvider {
    override fun ui(): CoroutineDispatcher = Dispatchers.Main

    override fun io(): CoroutineDispatcher = Dispatchers.IO
}