package com.elchaninov.gbprofessionaldevelopment.model.datasource

import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel

//  Источник данных для репозитория (Интернет, БД и т. п.)
interface DataSourceLocal<T> : DataSourceRemote<T>{
    fun saveData(list: List<DataModel>)
}