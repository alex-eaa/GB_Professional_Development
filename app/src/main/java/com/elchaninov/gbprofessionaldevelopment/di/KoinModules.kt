package com.elchaninov.gbprofessionaldevelopment.di

import androidx.room.Room
import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.model.datasource.DataSourceLocal
import com.elchaninov.gbprofessionaldevelopment.model.datasource.DataSourceRemote
import com.elchaninov.gbprofessionaldevelopment.model.datasource.RetrofitImplementation
import com.elchaninov.gbprofessionaldevelopment.model.datasource.RoomDataBaseImplementation
import com.elchaninov.gbprofessionaldevelopment.model.datasource.room.DBStorage
import com.elchaninov.gbprofessionaldevelopment.view.MainViewModel
import com.elchaninov.gbprofessionaldevelopment.viewmodel.MainInteractor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

// Для удобства создадим две переменные: в одной находятся зависимости,
// используемые во всём приложении, во второй - зависимости конкретного экрана
val application = module {

    single {
        Room.databaseBuilder(androidContext(), DBStorage::class.java, "translate.db")
            .build().getTranslationDao()
    }
    single<DataSourceLocal<List<DataModel>>> { RoomDataBaseImplementation(get()) }
    single<DataSourceRemote<List<DataModel>>> { RetrofitImplementation() }
}

val mainScreen = module {
    factory { MainInteractor(get(), get()) }
    factory { MainViewModel(get()) }
}
