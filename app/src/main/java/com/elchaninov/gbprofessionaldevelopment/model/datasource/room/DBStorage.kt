package com.elchaninov.gbprofessionaldevelopment.model.datasource.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TranslationEntity::class], version = 1, exportSchema = false)
abstract class DBStorage : RoomDatabase() {
    abstract fun getTranslationDao(): TranslationDao
}