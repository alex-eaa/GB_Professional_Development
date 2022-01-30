package com.elchaninov.gbprofessionaldevelopment.viewmodel


//  Ещё выше стоит интерактор. Здесь уже чистая бизнес-логика
interface Interactor<T> {
    //  Use Сase: получение данных для вывода на экран
//  Используем RxJava
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}