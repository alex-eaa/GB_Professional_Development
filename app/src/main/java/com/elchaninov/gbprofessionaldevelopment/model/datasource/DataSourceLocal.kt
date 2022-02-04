package com.elchaninov.gbprofessionaldevelopment.model.datasource

interface DataSourceLocal<T> {
    suspend fun getData(word: String): T
    suspend fun saveData(list: T)
    suspend fun getTranslationFavorite(word: String): Boolean
    suspend fun toggleTranslationFavorite(word: String): Boolean
}