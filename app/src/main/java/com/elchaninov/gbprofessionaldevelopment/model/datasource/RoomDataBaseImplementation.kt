package com.elchaninov.gbprofessionaldevelopment.model.datasource

import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.model.datasource.room.EntityMeaning
import com.elchaninov.gbprofessionaldevelopment.model.datasource.room.TranslationDao
import com.elchaninov.gbprofessionaldevelopment.model.datasource.room.map

class RoomDataBaseImplementation(private val translationDao: TranslationDao) :
    DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> =
        translationDao.getTranslationWhitsMeaning("$word%").map { translationWhitsMeaning ->
                map(translationWhitsMeaning)
            }

    override suspend fun saveData(list: List<DataModel>) {
        translationDao.saveTranslation(list.map { map(it) })

        list.map { dataModel ->
            val listForSave: MutableList<EntityMeaning> = mutableListOf()

            dataModel.meanings?.let {
                listForSave.addAll(it.map { meaning -> map(meaning, dataModel) })
            }

            translationDao.saveMeaning(listForSave)
        }
    }
}