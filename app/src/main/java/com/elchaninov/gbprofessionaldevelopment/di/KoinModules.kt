package com.elchaninov.gbprofessionaldevelopment.di

import androidx.room.Room
import com.elchaninov.gbprofessionaldevelopment.data.DataSourceLocal
import com.elchaninov.gbprofessionaldevelopment.data.DataSourceRemote
import com.elchaninov.gbprofessionaldevelopment.data.RetrofitImplementation
import com.elchaninov.gbprofessionaldevelopment.data.RoomDataBaseImplementation
import com.elchaninov.gbprofessionaldevelopment.data.room.DBStorage
import com.elchaninov.gbprofessionaldevelopment.utils.StringProvider
import com.elchaninov.gbprofessionaldevelopment.view.descriptionscreen.DescriptionInteractor
import com.elchaninov.gbprofessionaldevelopment.view.descriptionscreen.DescriptionViewModel
import com.elchaninov.gbprofessionaldevelopment.view.favorite.FavoriteInteractor
import com.elchaninov.gbprofessionaldevelopment.view.favorite.FavoriteViewModel
import com.elchaninov.gbprofessionaldevelopment.view.history.HistoryInteractor
import com.elchaninov.gbprofessionaldevelopment.view.history.HistoryViewModel
import com.elchaninov.gbprofessionaldevelopment.view.main.MainInteractor
import com.elchaninov.gbprofessionaldevelopment.view.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
    single {
        Room.databaseBuilder(androidContext(), DBStorage::class.java, "translate.db")
            .build().getTranslationDao()
    }
    single<DataSourceLocal> { RoomDataBaseImplementation(get()) }
    single<DataSourceRemote> { RetrofitImplementation() }
    single { StringProvider(androidContext()) }
}

val mainScreen = module {
    factory { MainInteractor(get(), get()) }
    viewModel { MainViewModel(get()) }
}

val historyScreen = module {
    factory { HistoryInteractor(get()) }
    viewModel { HistoryViewModel(get()) }
}

val descriptionScreen = module {
    factory { DescriptionInteractor(get()) }
    viewModel { DescriptionViewModel(get()) }
}

val favoriteScreen = module {
    factory { FavoriteInteractor(get()) }
    viewModel { FavoriteViewModel(get()) }
}
