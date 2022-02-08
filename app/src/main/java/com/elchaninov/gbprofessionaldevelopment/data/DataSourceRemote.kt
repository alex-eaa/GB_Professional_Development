package com.elchaninov.gbprofessionaldevelopment.data

import com.elchaninov.model.dto.DataModelDto

//  Источник данных для репозитория (Интернет, БД и т. п.)
interface DataSourceRemote {
    suspend fun getData(word: String): List<DataModelDto>
}