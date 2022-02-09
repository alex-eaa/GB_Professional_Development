package com.elchaninov.gbprofessionaldevelopment.di

import androidx.room.Room
import com.elchaninov.gbprofessionaldevelopment.view.descriptionscreen.DescriptionInteractor
import com.elchaninov.gbprofessionaldevelopment.view.descriptionscreen.DescriptionViewModel
import com.elchaninov.favorite.favorite.FavoriteInteractor
import com.elchaninov.favorite.favorite.FavoriteViewModel
import com.elchaninov.gbprofessionaldevelopment.view.history.HistoryInteractor
import com.elchaninov.gbprofessionaldevelopment.view.history.HistoryViewModel
import com.elchaninov.gbprofessionaldevelopment.view.main.MainInteractor
import com.elchaninov.gbprofessionaldevelopment.view.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
    single {
        Room.databaseBuilder(androidContext(), com.elchaninov.repository.room.DBStorage::class.java, "translate.db")
            .build().getTranslationDao()
    }
    single<com.elchaninov.repository.DataSourceLocal> {
        com.elchaninov.repository.RoomDataBaseImplementation(
            get()
        )
    }
    single<com.elchaninov.repository.DataSourceRemote> { com.elchaninov.repository.RetrofitImplementation() }
    single { com.elchaninov.utils.StringProvider(androidContext()) }
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
