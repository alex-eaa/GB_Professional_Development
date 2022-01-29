package com.elchaninov.gbprofessionaldevelopment.model.datasource

import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import io.reactivex.rxjava3.core.Single

//  Источник данных для репозитория (Интернет, БД и т. п.)
interface DataSourceLocal<T>{
    fun getData(word: String): Single<T>
    fun saveData(list: List<DataModel>)
}