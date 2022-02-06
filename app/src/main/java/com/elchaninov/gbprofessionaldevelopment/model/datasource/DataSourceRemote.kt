package com.elchaninov.gbprofessionaldevelopment.model.datasource

import com.elchaninov.gbprofessionaldevelopment.model.data.DataModelDto

//  Источник данных для репозитория (Интернет, БД и т. п.)
interface DataSourceRemote {
    suspend fun getData(word: String): List<DataModelDto>
}