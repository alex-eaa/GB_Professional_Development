package com.elchaninov.gbprofessionaldevelopment.view.main

import com.elchaninov.model.toDataModel
import com.elchaninov.model.usermodel.DataModel
import com.example.core.viewmodel.Interactor

class MainInteractor(
    private val remoteRepository: com.elchaninov.repository.DataSourceRemote,
    private val localRepository: com.elchaninov.repository.DataSourceLocal
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
