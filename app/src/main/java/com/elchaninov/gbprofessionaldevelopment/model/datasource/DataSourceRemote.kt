package com.elchaninov.gbprofessionaldevelopment.model.datasource

import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel

//  Источник данных для репозитория (Интернет, БД и т. п.)
interface DataSourceRemote<T>{
    suspend fun getData(word: String): List<DataModel>
}