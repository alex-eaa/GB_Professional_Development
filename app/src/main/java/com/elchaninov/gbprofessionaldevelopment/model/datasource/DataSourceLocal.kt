package com.elchaninov.gbprofessionaldevelopment.model.datasource

import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.model.datasource.room.EntityTranslation

//  Источник данных для репозитория (Интернет, БД и т. п.)
interface DataSourceLocal<T>{
    suspend fun getData(word: String): T
    suspend fun saveData(list: List<DataModel>)
    suspend fun getTranslation(word: String) : EntityTranslation
    suspend fun updateTranslation(entityTranslation: EntityTranslation)
}