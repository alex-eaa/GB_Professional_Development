package com.elchaninov.gbprofessionaldevelopment.di

import androidx.room.Room
import com.elchaninov.descriptionScreen.DescriptionActivity
import com.elchaninov.descriptionScreen.DescriptionInteractor
import com.elchaninov.descriptionScreen.DescriptionViewModel
import com.elchaninov.favorite.favorite.FavoriteActivity
import com.elchaninov.favorite.favorite.FavoriteInteractor
import com.elchaninov.favorite.favorite.FavoriteViewModel
import com.elchaninov.gbprofessionaldevelopment.view.main.MainActivity
import com.elchaninov.gbprofessionaldevelopment.view.main.MainInteractor
import com.elchaninov.gbprofessionaldevelopment.view.main.MainViewModel
import com.elchaninov.historyscreen.HistoryActivity
import com.elchaninov.historyscreen.HistoryInteractor
import com.elchaninov.historyscreen.HistoryViewModel
import com.elchaninov.utils.OnlineLiveData
import com.example.core.Settings
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
    single {
        Room.databaseBuilder(androidContext(),
            com.elchaninov.repository.room.DBStorage::class.java,
            "translate.db")
            .build().getTranslationDao()
    }
    single<com.elchaninov.repository.DataSourceLocal> {
        com.elchaninov.repository.RoomDataBaseImplementation(
            get()
        )
    }
    single<com.elchaninov.repository.DataSourceRemote> { com.elchaninov.repository.RetrofitImplementation() }
    single { com.elchaninov.utils.StringProvider(androidContext()) }
    single { OnlineLiveData(androidContext()) }
}

val mainScreen = module {
    scope<MainActivity> {
        scoped { Settings(get()) }
        scoped { MainInteractor(get(), get()) }
        viewModel { MainViewModel(get()) }
    }
}

val historyScreen = module {
    scope<HistoryActivity> {
        scoped { HistoryInteractor(get()) }
        viewModel { HistoryViewModel(get()) }
    }
}

val descriptionScreen = module {
    scope<DescriptionActivity> {
        scoped { DescriptionInteractor(get()) }
        viewModel { DescriptionViewModel(get()) }
    }
}

val favoriteScreen = module {
    scope<FavoriteActivity> {
        scoped { FavoriteInteractor(get()) }
        viewModel { FavoriteViewModel(get()) }
    }
}
