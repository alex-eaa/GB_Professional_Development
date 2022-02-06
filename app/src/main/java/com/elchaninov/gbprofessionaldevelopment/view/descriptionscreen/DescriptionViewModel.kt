package com.elchaninov.gbprofessionaldevelopment.view.descriptionscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class DescriptionViewModel(private val interactor: DescriptionInteractor) : ViewModel() {

    private val _liveDataForViewToObserve: MutableLiveData<Boolean> = MutableLiveData()
    val liveDataForViewToObserve: LiveData<Boolean> get() = _liveDataForViewToObserve

    fun getData(word: String) {
        viewModelScope.launch(exceptionHandler) {
            _liveDataForViewToObserve.postValue(interactor.getData(word, false).favorite)
        }
    }

    fun toggleEntityTranslation(word: String?) {
        word?.let {
            viewModelScope.launch(exceptionHandler) {
                _liveDataForViewToObserve.postValue(interactor.toggleTranslationFavorite(word).favorite)
            }
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    private fun handleError(error: Throwable) {
        error.stackTrace
    }
}
