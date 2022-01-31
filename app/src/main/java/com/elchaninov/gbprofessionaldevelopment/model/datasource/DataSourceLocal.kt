package com.elchaninov.gbprofessionaldevelopment.model.datasource

import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel

//  Источник данных для репозитория (Интернет, БД и т. п.)
interface DataSourceLocal<T>{
    suspend fun getData(word: String): T
    suspend fun saveData(list: List<DataModel>)
}