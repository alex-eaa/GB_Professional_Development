package com.elchaninov.gbprofessionaldevelopment.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.elchaninov.model.room.EntityMeaning
import com.elchaninov.model.room.EntityTranslation

@Database(entities = [EntityTranslation::class, EntityMeaning::class], version = 1, exportSchema = false)
abstract class DBStorage : RoomDatabase() {
    abstract fun getTranslationDao(): TranslationDao
}