package com.elchaninov.gbprofessionaldevelopment.di

import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.model.datasource.DataSource
import com.elchaninov.gbprofessionaldevelopment.model.datasource.RetrofitImplementation
import com.elchaninov.gbprofessionaldevelopment.model.datasource.RoomDataBaseImplementation
import com.elchaninov.gbprofessionaldevelopment.model.repository.Repository
import com.elchaninov.gbprofessionaldevelopment.model.repository.RepositoryImplementation
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    fun provideRepositoryRemote(@Named(NAME_REMOTE) dataSourceRemote: DataSource<List<DataModel>>): Repository<List<DataModel>> =
        RepositoryImplementation(dataSourceRemote)

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    fun provideRepositoryLocal(@Named(NAME_LOCAL) dataSourceLocal: DataSource<List<DataModel>>): Repository<List<DataModel>> =
        RepositoryImplementation(dataSourceLocal)

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    fun provideDataSourceRemote(): DataSource<List<DataModel>> = RetrofitImplementation()

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    fun provideDataSourceLocal(): DataSource<List<DataModel>> = RoomDataBaseImplementation()
}
