package com.elchaninov.gbprofessionaldevelopment.viewmodel

import io.reactivex.rxjava3.core.Single


//  Ещё выше стоит интерактор. Здесь уже чистая бизнес-логика
interface Interactor<T> {
//  Use Сase: получение данных для вывода на экран
//  Используем RxJava
    fun getData(word: String, fromRemoteSource: Boolean): Single<T>
}