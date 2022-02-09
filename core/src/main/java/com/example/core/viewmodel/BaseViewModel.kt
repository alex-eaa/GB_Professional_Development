package com.example.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.AppState

abstract class BaseViewModel<T : AppState>(
    protected val _liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData()
) : ViewModel() {

    val liveDataForViewToObserve: LiveData<T> get() = _liveDataForViewToObserve

    abstract fun getData(word: String? = null, isOnline: Boolean)

    abstract fun handleError(error: Throwable)
}