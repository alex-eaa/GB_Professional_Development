package com.elchaninov.gbprofessionaldevelopment.view.descriptionscreen

import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.model.datasource.DataSourceLocal
import com.elchaninov.gbprofessionaldevelopment.viewmodel.Interactor
import com.elchaninov.gbprofessionaldevelopment.viewmodel.InteractorToggleFavorite

class DescriptionInteractor(
    private val localRepository: DataSourceLocal
) : Interactor<DataModel>, InteractorToggleFavorite<DataModel>  {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): DataModel {
        return localRepository.getTranslationFavorite(word)
    }

    override suspend fun toggleTranslationFavorite(word: String): DataModel =
        localRepository.toggleTranslationFavorite(word)
}
