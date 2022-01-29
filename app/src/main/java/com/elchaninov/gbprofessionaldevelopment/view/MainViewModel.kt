package com.elchaninov.gbprofessionaldevelopment.view

import com.elchaninov.gbprofessionaldevelopment.model.data.AppState
import com.elchaninov.gbprofessionaldevelopment.viewmodel.BaseViewModel
import com.elchaninov.gbprofessionaldevelopment.viewmodel.MainInteractor
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import javax.inject.Inject

class MainViewModel @Inject constructor(private val interactor: MainInteractor) :
    BaseViewModel<AppState>() {

    // В этой переменной хранится последнее состояние Activity
    private var appState: AppState? = null
    private var searchWord: String? = null

    override fun getData(word: String?, isOnline: Boolean) {
        if (word != null) searchWord = word

        searchWord?.let {
            compositeDisposable.add(
                interactor.getData(it, isOnline)
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .doOnSubscribe {
                        liveDataForViewToObserve.value = AppState.Loading(null)
                    }
                    .subscribeWith(getSingleObserver())
            )
        }
    }

    private fun getSingleObserver(): DisposableSingleObserver<AppState> {
        return object : DisposableSingleObserver<AppState>() {
            override fun onSuccess(state: AppState) {
                appState = state
                liveDataForViewToObserve.value = state
            }

            override fun onError(e: Throwable) {
                liveDataForViewToObserve.value = AppState.Error(e)
            }
        }
    }
}