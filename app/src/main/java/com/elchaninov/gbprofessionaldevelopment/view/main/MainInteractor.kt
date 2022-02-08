package com.elchaninov.gbprofessionaldevelopment.view.main

import com.elchaninov.gbprofessionaldevelopment.data.DataSourceLocal
import com.elchaninov.gbprofessionaldevelopment.data.DataSourceRemote
import com.elchaninov.gbprofessionaldevelopment.model.usermodel.DataModel
import com.elchaninov.gbprofessionaldevelopment.model.toDataModel
import com.elchaninov.gbprofessionaldevelopment.viewmodel.Interactor

class MainInteractor(
    private val remoteRepository: DataSourceRemote,
    private val localRepository: DataSourceLocal
) : Interactor<List<DataModel>> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): List<DataModel> {
        return if (fromRemoteSource) {
            val listDataModelDto = remoteRepository.getData(word)
            if (!listDataModelDto.isNullOrEmpty()) localRepository.saveData(listDataModelDto)
            listDataModelDto.map { dataModelDto -> dataModelDto.toDataModel() }
        } else {
            localRepository.getData(word)
        }
    }
}
