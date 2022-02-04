package com.elchaninov.gbprofessionaldevelopment.model.datasource.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface TranslationDao {

    @Insert(onConflict = REPLACE)
    suspend fun saveTranslation(translations: List<EntityTranslation>)

    @Insert(onConflict = REPLACE)
    suspend fun saveMeaning(meaning: List<EntityMeaning>)

    @Query("SELECT * FROM TranslationTable WHERE text LIKE :word ORDER BY text ASC")
    suspend fun getTranslationWhitsMeaning(word: String): List<TranslationWhitsMeaning>


    @Query("SELECT favorite FROM TranslationTable WHERE text = :word")
    suspend fun getTranslationFavorite(word: String): Boolean

    @Query("UPDATE TranslationTable SET favorite = :favorite")
    suspend fun updateTranslationFavorite(favorite: Boolean)

    @Transaction
    suspend fun toggleTranslationFavorite(word: String): Boolean {
        val currentFavorite = getTranslationFavorite(word)
        updateTranslationFavorite(!currentFavorite)
        return getTranslationFavorite(word)
    }
}