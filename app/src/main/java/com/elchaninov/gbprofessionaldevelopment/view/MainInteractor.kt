package com.elchaninov.gbprofessionaldevelopment.view

import com.elchaninov.gbprofessionaldevelopment.model.data.AppState
import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.model.repository.Repository
import com.elchaninov.gbprofessionaldevelopment.viewmodel.Interactor
import io.reactivex.rxjava3.core.Observable

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word)
                .map {
                    AppState.Success(it)
                }
        } else {
            localRepository.getData(word)
                .map {
                    AppState.Success(it)
                }
        }
    }
}
