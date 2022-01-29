package com.elchaninov.gbprofessionaldevelopment.viewmodel

import com.elchaninov.gbprofessionaldevelopment.model.data.AppState
import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.model.datasource.DataSourceLocal
import com.elchaninov.gbprofessionaldevelopment.model.datasource.DataSourceRemote
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MainInteractor @Inject constructor(
    private val remoteRepository: DataSourceRemote<List<DataModel>>,
    private val localRepository: DataSourceLocal<List<DataModel>>
) : Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Single<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word)
                .map {
                    localRepository.saveData(it)
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
