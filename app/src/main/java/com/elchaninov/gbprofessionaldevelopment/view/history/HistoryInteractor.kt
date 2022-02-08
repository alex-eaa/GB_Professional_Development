package com.elchaninov.gbprofessionaldevelopment.view.history

import com.elchaninov.gbprofessionaldevelopment.data.DataSourceLocal
import com.elchaninov.model.usermodel.DataModel
import com.elchaninov.gbprofessionaldevelopment.viewmodel.Interactor
import com.elchaninov.gbprofessionaldevelopment.viewmodel.InteractorToggleFavorite

class HistoryInteractor(
    private val localRepository: DataSourceLocal
) : Interactor<List<DataModel>>, InteractorToggleFavorite<DataModel> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): List<DataModel> {
        return localRepository.getData(word)
    }

    override suspend fun toggleTranslationFavorite(word: String): DataModel {
        return localRepository.toggleTranslationFavorite(word)
    }
}
