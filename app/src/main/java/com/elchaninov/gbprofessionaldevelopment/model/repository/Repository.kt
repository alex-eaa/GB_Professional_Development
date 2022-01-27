package com.elchaninov.gbprofessionaldevelopment.model.repository

import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import io.reactivex.rxjava3.core.Observable


//  Репозиторий представляет собой слой получения и хранения данных, которые он
//  передаёт интерактору
interface Repository<T> {

    fun getData(word: String): Observable<T>

    fun saveData(ataModels: List<DataModel>)
}