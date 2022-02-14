package com.elchaninov.favorite.favorite

import androidx.lifecycle.viewModelScope
import com.elchaninov.model.usermodel.DataModel
import com.example.core.AppState
import com.example.core.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class FavoriteViewModel(private val interactor: FavoriteInteractor) : BaseViewModel<AppState>() {

    override fun getData(word: String?, isOnline: Boolean?) {
        word?.also { searchWord = it }
        isOnline?.also { searchOnline = it }

        _liveDataForViewToObserve.postValue(AppState.Loading(null))
        viewModelScope.launch(exceptionHandler) {
            startInteractor(searchWord, searchOnline)
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
            dataModel.text.let {
                interactor.toggleTranslationFavorite(it)
                startInteractor(searchWord, false)
            }
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
        else if (dataModel[0].text.isEmpty() || dataModel[0].meanings.isNullOrEmpty()) AppState.Empty
        else AppState.Success(dataModel)
}
