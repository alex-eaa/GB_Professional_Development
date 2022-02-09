package com.elchaninov.gbprofessionaldevelopment.view.main

import androidx.lifecycle.viewModelScope
import com.elchaninov.model.usermodel.DataModel
import com.example.core.AppState
import com.example.core.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainViewModel(private val interactor: MainInteractor) : BaseViewModel<AppState>() {

    private var searchWord: String? = null

    override fun getData(word: String?, isOnline: Boolean) {
        if (word != null) searchWord = word

        searchWord?.let {
            _liveDataForViewToObserve.postValue(AppState.Loading(null))
            viewModelScope.launch {
                startInteractor(it, isOnline)
                    .catch { e ->
                        handleError(e)
                    }
                    .collect {
                        _liveDataForViewToObserve.postValue(it)
                    }
            }
        }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) =
        flow {
            emit(
                parseSearchResult(interactor.getData(word, isOnline))
            )
        }

    override fun handleError(error: Throwable) {
        _liveDataForViewToObserve.postValue(AppState.Error(error))
    }

    private fun parseSearchResult(dataModel: List<DataModel>): AppState =
        if (dataModel.isNullOrEmpty()) AppState.Empty
        else if (dataModel[0].text.isNullOrEmpty() || dataModel[0].meanings.isNullOrEmpty()) AppState.Empty
        else AppState.Success(dataModel)

}