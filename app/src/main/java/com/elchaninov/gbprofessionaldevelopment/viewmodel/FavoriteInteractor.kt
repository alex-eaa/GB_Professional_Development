package com.elchaninov.gbprofessionaldevelopment.viewmodel


//  Ещё выше стоит интерактор. Здесь уже чистая бизнес-логика
interface FavoriteInteractor {
    suspend fun getTranslationFavorite(word: String): Boolean
    suspend fun toggleTranslationFavorite(word: String): Boolean
}