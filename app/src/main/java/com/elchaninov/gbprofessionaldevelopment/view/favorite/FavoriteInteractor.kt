package com.elchaninov.gbprofessionaldevelopment.view.favorite

import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.model.datasource.DataSourceLocal
import com.elchaninov.gbprofessionaldevelopment.viewmodel.Interactor

class FavoriteInteractor(
    private val localRepository: DataSourceLocal
) : Interactor<List<DataModel>> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): List<DataModel> {
        return localRepository.getFavoriteData(word)
    }

    suspend fun toggleTranslationFavorite(dataModel: DataModel): Boolean {
        dataModel.text?.let {
            return localRepository.toggleTranslationFavorite(it)
        }
        return false
    }
}
