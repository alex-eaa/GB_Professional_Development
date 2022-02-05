package com.elchaninov.gbprofessionaldevelopment.view.descriptionscreen

import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.model.datasource.DataSourceLocal
import com.elchaninov.gbprofessionaldevelopment.viewmodel.FavoriteInteractor

class DescriptionInteractor(
    private val localRepository: DataSourceLocal<List<DataModel>>
) : FavoriteInteractor {

    override suspend fun getTranslationFavorite(word: String): Boolean =
        localRepository.getTranslationFavorite(word)

    override suspend fun toggleTranslationFavorite(word: String): Boolean =
        localRepository.toggleTranslationFavorite(word)
}
