package com.elchaninov.gbprofessionaldevelopment.di

import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.model.datasource.DataSourceLocal
import com.elchaninov.gbprofessionaldevelopment.model.datasource.DataSourceRemote
import com.elchaninov.gbprofessionaldevelopment.model.datasource.RetrofitImplementation
import com.elchaninov.gbprofessionaldevelopment.model.datasource.RoomDataBaseImplementation
import com.elchaninov.gbprofessionaldevelopment.model.datasource.room.TranslationDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideDataSourceLocal(translationDao: TranslationDao): DataSourceLocal<List<DataModel>> =
        RoomDataBaseImplementation(translationDao)

    @Provides
    @Singleton
    fun provideDataSourceRemote(): DataSourceRemote<List<DataModel>> =
        RetrofitImplementation()
}
