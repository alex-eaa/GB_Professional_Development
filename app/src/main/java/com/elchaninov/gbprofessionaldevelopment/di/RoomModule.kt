package com.elchaninov.gbprofessionaldevelopment.di

import android.content.Context
import androidx.room.Room
import com.elchaninov.gbprofessionaldevelopment.model.datasource.room.DBStorage
import com.elchaninov.gbprofessionaldevelopment.model.datasource.room.TranslationDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideDatabase(app: Context): DBStorage =
        Room.databaseBuilder(app, DBStorage::class.java, "github.db")
            .build()

    @Singleton
    @Provides
    fun provideTranslationDao(dBStorage: DBStorage): TranslationDao =
        dBStorage.getTranslationDao()
}