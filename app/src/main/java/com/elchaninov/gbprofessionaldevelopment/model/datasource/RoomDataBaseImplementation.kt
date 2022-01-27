package com.elchaninov.gbprofessionaldevelopment.model.datasource

import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.model.datasource.room.EntityMeaning
import com.elchaninov.gbprofessionaldevelopment.model.datasource.room.TranslationDao
import com.elchaninov.gbprofessionaldevelopment.model.datasource.room.map
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class RoomDataBaseImplementation @Inject constructor(
    private val translationDao: TranslationDao,
) : DataSourceLocal<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return translationDao.getTranslationWhitsMeaning("$word%")
            .map {
                it.map { translationWhitsMeaning ->
                    map(translationWhitsMeaning)
                }
            }
    }

    override fun saveData(list: List<DataModel>) {
        translationDao.saveTranslation(list.map { map(it) })

        list.map { dataModel ->
            val list: MutableList<EntityMeaning> = mutableListOf()

            dataModel.meanings?.let {
                list.addAll(it.map { meaning ->
                    map(meaning, dataModel)
                })
            }

            translationDao.saveMeaning(list)
        }
    }
}