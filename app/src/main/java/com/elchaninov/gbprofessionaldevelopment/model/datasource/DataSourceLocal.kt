package com.elchaninov.gbprofessionaldevelopment.model.datasource

import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import io.reactivex.rxjava3.core.Observable

//  Источник данных для репозитория (Интернет, БД и т. п.)
interface DataSourceLocal<T>{
    fun getData(word: String): Observable<T>
    fun saveData(list: List<DataModel>)
}