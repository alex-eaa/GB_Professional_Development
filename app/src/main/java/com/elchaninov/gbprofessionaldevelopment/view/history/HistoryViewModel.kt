package com.elchaninov.gbprofessionaldevelopment.view.history

import androidx.lifecycle.viewModelScope
import com.elchaninov.gbprofessionaldevelopment.model.data.AppState
import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class HistoryViewModel(private val interactor: HistoryInteractor) : BaseViewModel<AppState>() {

    private var searchWord: String? = ""

    override fun getData(word: String?, isOnline: Boolean) {
        if (word != null) searchWord = word

        searchWord?.let {
            _liveDataForViewToObserve.postValue(AppState.Loading(null))
            viewModelScope.launch(exceptionHandler) {
                startInteractor(it, isOnline)
            }
        }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) =
        _liveDataForViewToObserve.postValue(
            parseSearchResult(
                interactor.getData(word, isOnline)
            )
        )

    fun toggleEntityTranslation(dataModel: DataModel) {
        viewModelScope.launch(exceptionHandler) {
            interactor.toggleTranslationFavorite(dataModel)
            searchWord?.let { startInteractor(it, false) }
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    override fun handleError(error: Throwable) {
        _liveDataForViewToObserve.postValue(AppState.Error(error))
    }

    private fun parseSearchResult(dataModel: List<DataModel>): AppState =
        if (dataModel.isNullOrEmpty()) AppState.Empty
        else if (dataModel[0].text.isNullOrEmpty() || dataModel[0].meanings.isNullOrEmpty()) AppState.Empty
        else AppState.Success(dataModel)
}
