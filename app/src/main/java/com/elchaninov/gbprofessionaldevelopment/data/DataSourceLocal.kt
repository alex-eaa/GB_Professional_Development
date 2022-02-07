package com.elchaninov.gbprofessionaldevelopment.data

import com.elchaninov.gbprofessionaldevelopment.model.dto.DataModelDto
import com.elchaninov.gbprofessionaldevelopment.model.usermodel.DataModel

interface DataSourceLocal {
    suspend fun getData(word: String): List<DataModel>
    suspend fun getFavoriteData(word: String): List<DataModel>
    suspend fun saveData(list: List<DataModelDto>)
    suspend fun getTranslationFavorite(word: String): DataModel
    suspend fun toggleTranslationFavorite(word: String): DataModel
}