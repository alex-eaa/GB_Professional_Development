package com.example.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.AppState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<T : AppState>(
    protected val _liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData(),
) : ViewModel() {

    val liveDataForViewToObserve: LiveData<T> get() = _liveDataForViewToObserve
    protected var searchWord: String = ""
    protected var searchOnline: Boolean = true

    abstract fun getData(word: String? = null, isOnline: Boolean?)

    abstract fun handleError(error: Throwable)

    private val queryStateFlow = MutableStateFlow("")

    private val job: Job = viewModelScope.launch {
        queryStateFlow.debounce(500)
            .filter { query ->
                return@filter query.isNotEmpty()
            }
            .distinctUntilChanged()
            .map { query ->
                getData(query, searchOnline)
            }
            .collect()
    }

    fun updateQueryStateFlow(searchWord: String, isOnline: Boolean) {
        searchOnline = isOnline
        queryStateFlow.value = searchWord
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}