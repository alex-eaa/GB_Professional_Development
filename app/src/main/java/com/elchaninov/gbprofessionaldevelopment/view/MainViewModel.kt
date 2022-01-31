package com.elchaninov.gbprofessionaldevelopment.view

import androidx.lifecycle.LiveData
import com.elchaninov.gbprofessionaldevelopment.model.data.AppState
import com.elchaninov.gbprofessionaldevelopment.viewmodel.BaseViewModel
import io.reactivex.rxjava3.observers.DisposableObserver
import javax.inject.Inject

class MainViewModel @Inject constructor(private val interactor: MainInteractor) :
    BaseViewModel<AppState>() {

    private var searchWord: String? = null

    override fun getData(word: String?, isOnline: Boolean): LiveData<AppState> {
        if (word != null) searchWord = word

        searchWord?.let {
            compositeDisposable.add(
                interactor.getData(it, isOnline)
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .doOnSubscribe {
                        liveDataForViewToObserve.value = AppState.Loading(null)
                    }
                    .subscribeWith(getObserver())
            )
        }
        return super.getData(word, isOnline)
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(state: AppState) {
                liveDataForViewToObserve.value = state
            }

            override fun onError(e: Throwable) {
                liveDataForViewToObserve.value = AppState.Error(e)
            }

            override fun onComplete() {
            }
        }
    }
}