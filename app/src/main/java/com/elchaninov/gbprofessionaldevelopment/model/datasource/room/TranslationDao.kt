package com.elchaninov.gbprofessionaldevelopment.model.datasource.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface TranslationDao {

    @Query("SELECT * FROM TranslationTable WHERE text = :word")
    fun getTranslation(word: String): Observable<List<TranslationEntity>>

    @Insert(onConflict = REPLACE)
    fun saveTranslation(translations: List<TranslationEntity>)
}