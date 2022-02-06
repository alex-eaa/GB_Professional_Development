package com.elchaninov.gbprofessionaldevelopment.view.descriptionscreen

import com.elchaninov.gbprofessionaldevelopment.model.datasource.DataSourceLocal

class DescriptionInteractor(
    private val localRepository: DataSourceLocal
) {

    suspend fun getTranslationFavorite(word: String): Boolean =
        localRepository.getTranslationFavorite(word)

    suspend fun toggleTranslationFavorite(word: String): Boolean =
        localRepository.toggleTranslationFavorite(word)
}
