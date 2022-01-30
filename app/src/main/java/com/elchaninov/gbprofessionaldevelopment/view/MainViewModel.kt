package com.elchaninov.gbprofessionaldevelopment.view

import com.elchaninov.gbprofessionaldevelopment.model.data.AppState
import com.elchaninov.gbprofessionaldevelopment.viewmodel.BaseViewModel
import com.elchaninov.gbprofessionaldevelopment.viewmodel.MainInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val interactor: MainInteractor) : BaseViewModel<AppState>() {

    private var searchWord: String? = null

    override fun getData(word: String?, isOnline: Boolean) {
        if (word != null) searchWord = word
        searchWord?.let {
            _liveDataForViewToObserve.postValue(AppState.Loading(null))
            cancelJob()
            viewModelCoroutineScope.launch { startInteractor(it, isOnline) }
        }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) =
        withContext(Dispatchers.IO) {
            _liveDataForViewToObserve.postValue(interactor.getData(word, isOnline))
        }

    override fun handleError(error: Throwable) {
        _liveDataForViewToObserve.value = AppState.Error(error)
    }
}