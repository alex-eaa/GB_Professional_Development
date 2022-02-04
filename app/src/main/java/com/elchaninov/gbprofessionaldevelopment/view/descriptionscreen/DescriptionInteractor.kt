package com.elchaninov.gbprofessionaldevelopment.view.descriptionscreen

import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.model.datasource.DataSourceLocal
import com.elchaninov.gbprofessionaldevelopment.model.datasource.room.EntityTranslation
import com.elchaninov.gbprofessionaldevelopment.viewmodel.Interactor

class DescriptionInteractor(
    private val localRepository: DataSourceLocal<List<DataModel>>,
) : Interactor<EntityTranslation> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): EntityTranslation {
        return localRepository.getTranslation(word)
    }

    suspend fun toggleTranslation(word: String) {
        localRepository.getTranslation(word).let {
            localRepository.updateTranslation(EntityTranslation(
                id = it.id, text = it.text, favorite = !it.favorite
            ))
        }
    }
}
