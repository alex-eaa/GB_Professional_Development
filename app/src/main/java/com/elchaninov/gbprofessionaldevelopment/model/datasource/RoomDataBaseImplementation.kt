package com.elchaninov.gbprofessionaldevelopment.model.datasource

import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.model.datasource.room.TranslationDao
import com.elchaninov.gbprofessionaldevelopment.model.datasource.room.mapToDataModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class RoomDataBaseImplementation @Inject constructor(
    private val translationDao: TranslationDao
) : LocalDataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return translationDao.getTranslation(word)
            .map {
                mapToDataModel(it)
            }
    }

    override fun saveData(list: List<DataModel>) {
        TODO("Not yet implemented")
    }
}