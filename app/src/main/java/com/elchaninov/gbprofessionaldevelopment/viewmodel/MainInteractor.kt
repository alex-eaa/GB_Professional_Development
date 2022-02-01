package com.elchaninov.gbprofessionaldevelopment.viewmodel

import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.model.datasource.DataSourceLocal
import com.elchaninov.gbprofessionaldevelopment.model.datasource.DataSourceRemote

class MainInteractor(
    private val remoteRepository: DataSourceRemote<List<DataModel>>,
    private val localRepository: DataSourceLocal<List<DataModel>>
) : Interactor<List<DataModel>> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): List<DataModel> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).also {
                if (!it.isNullOrEmpty()) localRepository.saveData(it)
            }
        } else {
            localRepository.getData(word)
        }
    }
}
