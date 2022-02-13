package com.elchaninov.model

import com.elchaninov.model.dto.DataModelDto
import com.elchaninov.model.dto.MeaningsDto
import com.elchaninov.model.dto.TranslationDto
import com.elchaninov.model.room.EntityMeaning
import com.elchaninov.model.room.EntityTranslation
import com.elchaninov.model.room.EntityTranslationWhitsMeaning
import com.elchaninov.model.usermodel.DataModel
import com.elchaninov.model.usermodel.Meanings
import com.elchaninov.model.usermodel.Translation

fun DataModelDto.toDataModel(): DataModel = DataModel(
    text = this.text ?: "",
    meanings = this.meanings?.map { meaningsDto -> meaningsDto.toMeanings() } ?: listOf()
)

fun MeaningsDto.toMeanings(): Meanings = Meanings(
    translation = this.translation?.toTranslation() ?: Translation(),
    imageUrl = this.imageUrl ?: ""
)

fun TranslationDto.toTranslation(): Translation = Translation(
    translation = this.translation ?: ""
)

fun DataModelDto.toEntityTranslation(): EntityTranslation = EntityTranslation(
    id = this.id,
    text = this.text,
    favorite = false
)

fun EntityTranslationWhitsMeaning.toDataModel(): DataModel = DataModel(
    text = this.text ?: "",
    meanings = this.meanings?.map { entityMeaning -> entityMeaning.toMeanings() } ?: listOf(),
    favorite = this.favorite
)

fun EntityMeaning.toMeanings(): Meanings = Meanings(
    translation = Translation(this.text ?: ""),
    imageUrl = this.imageUrl ?: ""
)

fun map(meaningsDto: MeaningsDto, dataModelDto: DataModelDto): EntityMeaning {
    return EntityMeaning(
        id = meaningsDto.id,
        text = meaningsDto.translation?.translation,
        imageUrl = meaningsDto.imageUrl,
        translationId = dataModelDto.id
    )
}


