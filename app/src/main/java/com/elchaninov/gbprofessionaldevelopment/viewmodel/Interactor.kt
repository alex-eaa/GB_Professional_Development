package com.elchaninov.gbprofessionaldevelopment.viewmodel


//  Ещё выше стоит интерактор. Здесь уже чистая бизнес-логика
interface Interactor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}