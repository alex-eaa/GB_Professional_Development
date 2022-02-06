package com.elchaninov.gbprofessionaldevelopment.view.history

import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.model.datasource.DataSourceLocal
import com.elchaninov.gbprofessionaldevelopment.viewmodel.FavoriteInteractor
import com.elchaninov.gbprofessionaldevelopment.viewmodel.Interactor

class HistoryInteractor(
    private val localRepository: DataSourceLocal
) : Interactor<List<DataModel>> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): List<DataModel> {
        return localRepository.getData(word)
    }

    suspend fun toggleTranslationFavorite(dataModel: DataModel): Boolean {
        dataModel.text?.let {
            return localRepository.toggleTranslationFavorite(it)
        }
        return false
    }
}
