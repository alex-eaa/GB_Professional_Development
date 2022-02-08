package com.elchaninov.gbprofessionaldevelopment.view.favorite

import com.elchaninov.gbprofessionaldevelopment.data.DataSourceLocal
import com.elchaninov.model.usermodel.DataModel
import com.elchaninov.gbprofessionaldevelopment.viewmodel.Interactor
import com.elchaninov.gbprofessionaldevelopment.viewmodel.InteractorToggleFavorite

class FavoriteInteractor(
    private val localRepository: DataSourceLocal
) : Interactor<List<DataModel>>, InteractorToggleFavorite<DataModel> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): List<DataModel> {
        return localRepository.getFavoriteData(word)
    }

    override suspend fun toggleTranslationFavorite(word: String): DataModel {
        return localRepository.toggleTranslationFavorite(word)
    }
}
