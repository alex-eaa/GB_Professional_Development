package com.elchaninov.gbprofessionaldevelopment.model.repository

import io.reactivex.rxjava3.core.Observable


//  Репозиторий представляет собой слой получения и хранения данных, которые он
//  передаёт интерактору
interface Repository<T> {

    fun getData(word: String): Observable<T>
}