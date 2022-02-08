package com.elchaninov.gbprofessionaldevelopment.view.history

import com.elchaninov.gbprofessionaldevelopment.viewmodel.Interactor
import com.elchaninov.gbprofessionaldevelopment.viewmodel.InteractorToggleFavorite
import com.elchaninov.model.usermodel.DataModel

class HistoryInteractor(
    private val localRepository: com.elchaninov.repository.DataSourceLocal
) : Interactor<List<DataModel>>, InteractorToggleFavorite<DataModel> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): List<DataModel> {
        return localRepository.getData(word)
    }

    override suspend fun toggleTranslationFavorite(word: String): DataModel {
        return localRepository.toggleTranslationFavorite(word)
    }
}
