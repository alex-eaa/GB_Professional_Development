package com.elchaninov.gbprofessionaldevelopment.model.datasource.room

import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.model.data.Meanings
import com.elchaninov.gbprofessionaldevelopment.model.data.Translation


fun mapToTranslationEntity(dataModels: List<DataModel>): List<TranslationEntity> =
    dataModels.map {
        TranslationEntity(
            id = it.id,
            text = it.text,
            translation = it.meanings?.get(0)?.translation?.translation,
            imageUrl = it.meanings?.get(0)?.imageUrl,
        )
    }

fun mapToDataModel(translationEntity: List<TranslationEntity>): List<DataModel> =
    translationEntity.map {
        DataModel(
            id = it.id,
            text = it.text,
            meanings = listOf(
                Meanings(
                    translation = Translation(it.translation),
                    imageUrl = it.imageUrl
                )
            )
        )
    }
