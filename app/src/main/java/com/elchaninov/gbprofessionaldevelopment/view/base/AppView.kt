package com.elchaninov.gbprofessionaldevelopment.view.base

import com.elchaninov.gbprofessionaldevelopment.model.data.AppState

//  Нижний уровень. View знает о контексте и фреймворке
interface AppView {

//    View имеет только один метод, в который приходит некое состояние приложения
    fun renderData(appState: AppState)
}