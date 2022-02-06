package com.elchaninov.gbprofessionaldevelopment.model.datasource

import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.model.data.DataModelDto
import com.elchaninov.gbprofessionaldevelopment.model.datasource.room.*

class RoomDataBaseImplementation(private val translationDao: TranslationDao) :
    DataSourceLocal {

    override suspend fun getData(word: String): List<DataModel> =
        translationDao.getTranslationWhitsMeaning("$word%").map { translationWhitsMeaning ->
            translationWhitsMeaning.toDataModel()
        }

    override suspend fun getFavoriteData(word: String): List<DataModel> =
        translationDao.getFavoriteTranslationWhitsMeaning("$word%").map { translationWhitsMeaning ->
            translationWhitsMeaning.toDataModel()
        }


    override suspend fun saveData(list: List<DataModelDto>) {
        val listEntityTranslation =
            list.map { dataModelDto -> dataModelDto.toEntityTranslation() }

        val listEntityMeaning: MutableList<EntityMeaning> = mutableListOf()
        list.map { dataModelDto ->
            dataModelDto.meanings?.let { listMeaningDto ->
                listEntityMeaning.addAll(listMeaningDto.map { meaningDto ->
                    map(meaningDto, dataModelDto)
                })
            }
        }

        translationDao.saveTranslationWhitsMeaning(listEntityTranslation, listEntityMeaning)
    }

    override suspend fun getTranslationFavorite(word: String): DataModel =
        translationDao.getTranslationWhitsMeaningByText(word).toDataModel()

    override suspend fun toggleTranslationFavorite(word: String): DataModel =
        translationDao.toggleTranslationFavorite(word).toDataModel()
}