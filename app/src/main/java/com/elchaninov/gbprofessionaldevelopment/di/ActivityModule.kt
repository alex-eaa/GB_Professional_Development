package com.elchaninov.gbprofessionaldevelopment.di

import com.elchaninov.gbprofessionaldevelopment.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}
