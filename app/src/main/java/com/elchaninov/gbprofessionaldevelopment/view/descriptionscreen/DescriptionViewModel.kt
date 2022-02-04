package com.elchaninov.gbprofessionaldevelopment.view.descriptionscreen

import androidx.lifecycle.viewModelScope
import com.elchaninov.gbprofessionaldevelopment.model.data.AppState
import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.model.datasource.room.EntityTranslation
import com.elchaninov.gbprofessionaldevelopment.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DescriptionViewModel(private val interactor: DescriptionInteractor) :
    BaseViewModel<AppState>() {

    override fun getData(word: String?, isOnline: Boolean) {
        word?.let {
            viewModelScope.launch(exceptionHandler) { startInteractor(it, isOnline) }
        }
    }

    fun toggleEntityTranslation(word: String) {
        viewModelScope.launch(exceptionHandler) {
            interactor.toggleTranslation(word)
        }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) =
        withContext(Dispatchers.IO) {
            _liveDataForViewToObserve.postValue(
                AppState.SuccessEntity(interactor.getData(word, isOnline))
            )
        }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    override fun handleError(error: Throwable) {
        _liveDataForViewToObserve.postValue(AppState.Error(error))
    }
}
