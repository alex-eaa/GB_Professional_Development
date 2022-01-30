package com.elchaninov.gbprofessionaldevelopment.viewmodel

import com.elchaninov.gbprofessionaldevelopment.model.data.AppState
import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.model.datasource.DataSourceLocal
import com.elchaninov.gbprofessionaldevelopment.model.datasource.DataSourceRemote

class MainInteractor(
    private val remoteRepository: DataSourceRemote<List<DataModel>>,
    private val localRepository: DataSourceLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return if (fromRemoteSource) {
            val dataModel = remoteRepository.getData(word)
            localRepository.saveData(dataModel)
            AppState.Success(dataModel)
        } else {
            AppState.Success(localRepository.getData(word))
        }
    }
}
