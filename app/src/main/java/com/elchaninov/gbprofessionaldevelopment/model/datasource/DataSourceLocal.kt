package com.elchaninov.gbprofessionaldevelopment.model.datasource

import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.model.data.DataModelDto

interface DataSourceLocal {
    suspend fun getData(word: String): List<DataModel>
    suspend fun saveData(list: List<DataModelDto>)
    suspend fun getTranslationFavorite(word: String): Boolean
    suspend fun toggleTranslationFavorite(word: String): Boolean
}