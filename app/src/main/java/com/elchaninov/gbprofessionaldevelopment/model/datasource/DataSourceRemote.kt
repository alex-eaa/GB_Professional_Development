package com.elchaninov.gbprofessionaldevelopment.model.datasource

import io.reactivex.rxjava3.core.Single

//  Источник данных для репозитория (Интернет, БД и т. п.)
interface DataSourceRemote<T>{
    fun getData(word: String): Single<T>
}