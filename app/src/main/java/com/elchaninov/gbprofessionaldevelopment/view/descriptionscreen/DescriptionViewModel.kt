package com.elchaninov.gbprofessionaldevelopment.view.descriptionscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.model.datasource.DataSourceLocal
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class DescriptionViewModel(private val dataSourceLocal: DataSourceLocal<List<DataModel>>) :
    ViewModel() {

    private val _liveDataForViewToObserve: MutableLiveData<Boolean> = MutableLiveData()
    val liveDataForViewToObserve: LiveData<Boolean> get() = _liveDataForViewToObserve

    fun getTranslationFavorite(word: String) {
        viewModelScope.launch(exceptionHandler) {
            _liveDataForViewToObserve.postValue(dataSourceLocal.getTranslationFavorite(word))
        }
    }

    fun toggleEntityTranslation(word: String?) {
        word?.let {
            viewModelScope.launch(exceptionHandler) {
                _liveDataForViewToObserve.postValue(dataSourceLocal.toggleTranslationFavorite(word))
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
