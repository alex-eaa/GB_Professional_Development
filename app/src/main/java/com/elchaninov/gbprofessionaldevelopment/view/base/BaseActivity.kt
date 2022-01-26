package com.elchaninov.gbprofessionaldevelopment.view.base

import androidx.appcompat.app.AppCompatActivity
import com.elchaninov.gbprofessionaldevelopment.model.data.AppState
import com.elchaninov.gbprofessionaldevelopment.utils.network.isOnline
import com.elchaninov.gbprofessionaldevelopment.viewmodel.BaseViewModel

abstract class BaseActivity<T : AppState> : AppCompatActivity() {

    // В каждой Активити будет своя ViewModel, которая наследуется от BaseViewModel
    abstract val model: BaseViewModel<T>

    // Каждая Активити будет отображать какие-то данные в соответствующем состоянии
    abstract fun renderData(appState: T)

    protected val isOnline: Boolean
        get() = isOnline(applicationContext)

}