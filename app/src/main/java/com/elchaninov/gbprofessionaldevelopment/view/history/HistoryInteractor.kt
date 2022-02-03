package com.elchaninov.gbprofessionaldevelopment.view.history

import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.model.datasource.DataSourceLocal
import com.elchaninov.gbprofessionaldevelopment.model.datasource.DataSourceRemote
import com.elchaninov.gbprofessionaldevelopment.viewmodel.Interactor

class HistoryInteractor(
    private val remoteRepository: DataSourceRemote<List<DataModel>>,
    private val localRepository: DataSourceLocal<List<DataModel>>,
) : Interactor<List<DataModel>> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): List<DataModel> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word)
        } else {
            localRepository.getData(word)
        }
    }
}
