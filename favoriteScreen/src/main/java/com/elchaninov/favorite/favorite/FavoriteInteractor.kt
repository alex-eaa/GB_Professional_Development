package com.elchaninov.favorite.favorite

import com.example.core.viewmodel.Interactor
import com.example.core.viewmodel.InteractorToggleFavorite
import com.elchaninov.model.usermodel.DataModel

class FavoriteInteractor(
    private val localRepository: com.elchaninov.repository.DataSourceLocal
) : Interactor<List<DataModel>>, InteractorToggleFavorite<DataModel> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): List<DataModel> {
        return localRepository.getFavoriteData(word)
    }

    override suspend fun toggleTranslationFavorite(word: String): DataModel {
        return localRepository.toggleTranslationFavorite(word)
    }
}
