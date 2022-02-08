package com.elchaninov.gbprofessionaldevelopment.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.elchaninov.model.room.EntityMeaning
import com.elchaninov.model.room.EntityTranslation
import com.elchaninov.model.room.EntityTranslationWhitsMeaning

@Dao
interface TranslationDao {

    @Insert(onConflict = IGNORE)
    suspend fun saveTranslation(translations: List<EntityTranslation>)

    @Insert(onConflict = REPLACE)
    suspend fun saveMeaning(meaning: List<EntityMeaning>)

    @Transaction
    suspend fun saveTranslationWhitsMeaning(
        translations: List<EntityTranslation>,
        meaning: List<EntityMeaning>
    ) {
        saveTranslation(translations)
        saveMeaning(meaning)
    }

    @Query("SELECT * FROM TranslationTable WHERE text LIKE :word ORDER BY text ASC")
    suspend fun getTranslationWhitsMeaning(word: String): List<EntityTranslationWhitsMeaning>

    @Query("SELECT * FROM TranslationTable WHERE favorite=:favorite AND text LIKE :word ORDER BY text ASC")
    suspend fun getFavoriteTranslationWhitsMeaning(
        word: String,
        favorite: Boolean = true
    ): List<EntityTranslationWhitsMeaning>

    @Query("SELECT * FROM TranslationTable WHERE text = :word")
    suspend fun getTranslationWhitsMeaningByText(word: String): EntityTranslationWhitsMeaning

    @Query("UPDATE TranslationTable SET favorite = :favorite WHERE text =:word")
    suspend fun updateTranslationFavorite(word: String, favorite: Boolean)

    @Transaction
    suspend fun toggleTranslationFavorite(word: String): EntityTranslationWhitsMeaning {
        val currentFavorite = getTranslationWhitsMeaningByText(word)
        updateTranslationFavorite(word, !currentFavorite.favorite)
        return getTranslationWhitsMeaningByText(word)
    }
}