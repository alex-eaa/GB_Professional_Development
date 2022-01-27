package com.elchaninov.gbprofessionaldevelopment.model.datasource

import io.reactivex.rxjava3.core.Observable

//  Источник данных для репозитория (Интернет, БД и т. п.)
interface DataSourceRemote<T>{
    fun getData(word: String): Observable<T>
}