package com.elchaninov.gbprofessionaldevelopment.view.descriptionscreen

import com.example.core.viewmodel.Interactor
import com.example.core.viewmodel.InteractorToggleFavorite
import com.elchaninov.model.usermodel.DataModel

class DescriptionInteractor(
    private val localRepository: com.elchaninov.repository.DataSourceLocal
) : Interactor<DataModel>, InteractorToggleFavorite<DataModel>  {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): DataModel {
        return localRepository.getTranslationFavorite(word)
    }

    override suspend fun toggleTranslationFavorite(word: String): DataModel =
        localRepository.toggleTranslationFavorite(word)
}
