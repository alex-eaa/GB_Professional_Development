package com.elchaninov.gbprofessionaldevelopment.model.datasource.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface TranslationDao {

    @Insert(onConflict = REPLACE)
    fun saveTranslation(translations: List<EntityTranslation>)

    @Insert(onConflict = REPLACE)
    fun saveMeaning(meaning: List<EntityMeaning>)

    @Query("SELECT * FROM TranslationTable WHERE text LIKE :word ORDER BY text ASC")
    suspend fun getTranslationWhitsMeaning(word: String): List<TranslationWhitsMeaning>
}