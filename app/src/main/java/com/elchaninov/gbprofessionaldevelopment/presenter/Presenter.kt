package com.elchaninov.gbprofessionaldevelopment.presenter

import com.elchaninov.gbprofessionaldevelopment.view.base.AppView
import com.elchaninov.gbprofessionaldevelopment.model.data.AppState

// На уровень выше находится презентер, который уже ничего не знает ни о контексте, ни о фреймворке
interface Presenter<T : AppState, V : AppView> {

    fun attachView(view: V)

    fun detachView(view: V)

    // Получение данных с флагом isOnline(из Интернета или нет)
    fun getData(word: String, isOnline: Boolean)
}