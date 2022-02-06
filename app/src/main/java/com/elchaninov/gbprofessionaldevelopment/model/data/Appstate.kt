package com.elchaninov.gbprofessionaldevelopment.model.data

import com.elchaninov.gbprofessionaldevelopment.model.datasource.room.EntityTranslation

sealed class AppState {
    data class Success(val data: List<DataModel>) : AppState()
    data class SuccessEntity(val data: EntityTranslation) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
    object Empty: AppState()
}
