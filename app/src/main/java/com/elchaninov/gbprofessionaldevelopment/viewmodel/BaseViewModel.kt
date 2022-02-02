package com.elchaninov.gbprofessionaldevelopment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elchaninov.gbprofessionaldevelopment.model.data.AppState
import com.elchaninov.gbprofessionaldevelopment.rx.SchedulerProvider
import kotlinx.coroutines.*

abstract class BaseViewModel<T : AppState>(
    protected val _liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData(),
    protected val schedulerProvider: SchedulerProvider = SchedulerProvider()
) : ViewModel() {

    val liveDataForViewToObserve: LiveData<T> get() = _liveDataForViewToObserve

    abstract fun getData(word: String? = null, isOnline: Boolean)

    abstract fun handleError(error: Throwable)
}