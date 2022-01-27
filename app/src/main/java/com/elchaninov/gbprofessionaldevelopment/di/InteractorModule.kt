package com.elchaninov.gbprofessionaldevelopment.di

import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.model.datasource.DataSourceLocal
import com.elchaninov.gbprofessionaldevelopment.model.datasource.DataSourceRemote
import com.elchaninov.gbprofessionaldevelopment.model.repository.Repository
import com.elchaninov.gbprofessionaldevelopment.viewmodel.MainInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    internal fun provideInteractor(
        repositoryRemote: DataSourceRemote<List<DataModel>>,
        repositoryLocal: DataSourceLocal<List<DataModel>>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}
