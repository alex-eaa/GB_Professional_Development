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
        val listForSave: MutableList<EntityMeaning> = mutableListOf()
        list.map { dataModel ->
            dataModel.meanings?.let {
                listForSave.addAll(it.map { meaning -> map(meaning, dataModel) })
            }
        }
        translationDao.saveTranslationWhitsMeaning(list.map { map(it) }, listForSave)
    }

    override suspend fun getTranslationFavorite(word: String): Boolean =
        translationDao.getTranslationFavorite(word)

    override suspend fun toggleTranslationFavorite(word: String): Boolean =
        translationDao.toggleTranslationFavorite(word)
}