package com.elchaninov.gbprofessionaldevelopment.viewmodel


//  Ещё выше стоит интерактор. Здесь уже чистая бизнес-логика
interface InteractorToggleFavorite<T> {
    suspend fun toggleTranslationFavorite(word: String): T
}