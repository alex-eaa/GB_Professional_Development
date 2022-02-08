package com.elchaninov.gbprofessionaldevelopment.view

import com.elchaninov.model.usermodel.DataModel

sealed class AppState {
    data class Success(val data: List<DataModel>) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
    object Empty: AppState()
}
