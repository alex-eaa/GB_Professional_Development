package com.elchaninov.descriptionScreen

import com.example.core.viewmodel.Interactor
import com.example.core.viewmodel.InteractorToggleFavorite
import com.elchaninov.model.usermodel.DataModel
import com.elchaninov.repository.DataSourceLocal

class DescriptionInteractor(
    private val localRepository: DataSourceLocal
) : Interactor<DataModel>, InteractorToggleFavorite<DataModel>  {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): DataModel {
        return localRepository.getTranslationFavorite(word)
    }

    override suspend fun toggleTranslationFavorite(word: String): DataModel =
        localRepository.toggleTranslationFavorite(word)
}
