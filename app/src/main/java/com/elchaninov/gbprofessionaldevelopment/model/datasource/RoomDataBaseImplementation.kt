package com.elchaninov.gbprofessionaldevelopment.model.datasource

import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.model.datasource.room.*

class RoomDataBaseImplementation(private val translationDao: TranslationDao) :
    DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> =
        translationDao.getTranslationWhitsMeaning("$word%").map { translationWhitsMeaning ->
            translationWhitsMeaning.toDataModel()
        }

    override suspend fun saveData(listDataModel: List<DataModel>) {
        val listEntityTranslation =
            listDataModel.map { dataModel -> dataModel.toEntityTranslation() }

        val listEntityMeaning: MutableList<EntityMeaning> = mutableListOf()
        listDataModel.map { dataModel ->
            dataModel.meanings?.let { listMeaning ->
                listEntityMeaning.addAll(listMeaning.map { meaning -> map(meaning, dataModel) })
            }
        }

        translationDao.saveTranslationWhitsMeaning(listEntityTranslation, listEntityMeaning)
    }

    override suspend fun getTranslationFavorite(word: String): Boolean =
        translationDao.getTranslationFavorite(word)

    override suspend fun toggleTranslationFavorite(word: String): Boolean =
        translationDao.toggleTranslationFavorite(word)
}