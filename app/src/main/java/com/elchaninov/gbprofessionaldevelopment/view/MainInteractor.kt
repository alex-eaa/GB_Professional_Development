package com.elchaninov.gbprofessionaldevelopment.view

import com.elchaninov.gbprofessionaldevelopment.di.NAME_LOCAL
import com.elchaninov.gbprofessionaldevelopment.di.NAME_REMOTE
import com.elchaninov.gbprofessionaldevelopment.model.data.AppState
import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.model.repository.Repository
import com.elchaninov.gbprofessionaldevelopment.viewmodel.Interactor
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Named

class MainInteractor @Inject constructor(
    @Named(NAME_REMOTE) val remoteRepository: Repository<List<DataModel>>,
    @Named(NAME_LOCAL) val localRepository: Repository<List<DataModel>>
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
