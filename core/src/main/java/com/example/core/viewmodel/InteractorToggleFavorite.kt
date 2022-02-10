package com.example.core.viewmodel


//  Ещё выше стоит интерактор. Здесь уже чистая бизнес-логика
interface InteractorToggleFavorite<T> {
    suspend fun toggleTranslationFavorite(word: String): T
}