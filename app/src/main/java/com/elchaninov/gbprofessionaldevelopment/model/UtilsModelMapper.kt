package com.elchaninov.gbprofessionaldevelopment.model

import com.elchaninov.gbprofessionaldevelopment.model.dto.DataModelDto
import com.elchaninov.gbprofessionaldevelopment.model.dto.MeaningsDto
import com.elchaninov.gbprofessionaldevelopment.model.dto.TranslationDto
import com.elchaninov.gbprofessionaldevelopment.model.room.EntityMeaning
import com.elchaninov.gbprofessionaldevelopment.model.room.EntityTranslation
import com.elchaninov.gbprofessionaldevelopment.model.room.EntityTranslationWhitsMeaning
import com.elchaninov.gbprofessionaldevelopment.model.usermodel.DataModel
import com.elchaninov.gbprofessionaldevelopment.model.usermodel.Meanings
import com.elchaninov.gbprofessionaldevelopment.model.usermodel.Translation

fun DataModelDto.toDataModel(): DataModel = DataModel(
    text = this.text,
    meanings = this.meanings?.map { meaningsDto -> meaningsDto.toMeanings() }
)

fun MeaningsDto.toMeanings(): Meanings = Meanings(
    translation = this.translation?.toTranslation(),
    imageUrl = this.imageUrl
)

fun TranslationDto.toTranslation(): Translation = Translation(
    translation = this.translation
)

fun DataModelDto.toEntityTranslation(): EntityTranslation = EntityTranslation(
    id = this.id,
    text = this.text,
    favorite = false
)

fun EntityTranslationWhitsMeaning.toDataModel(): DataModel = DataModel(
    text = this.text,
    meanings = this.meanings?.map { entityMeaning -> entityMeaning.toMeanings() },
    favorite = this.favorite
)

fun EntityMeaning.toMeanings(): Meanings = Meanings(
    translation = Translation(this.text),
    imageUrl = this.imageUrl
)

fun map(meaningsDto: MeaningsDto, dataModelDto: DataModelDto): EntityMeaning {
    return EntityMeaning(
        id = meaningsDto.id,
        text = meaningsDto.translation?.translation,
        imageUrl = meaningsDto.imageUrl,
        translationId = dataModelDto.id
    )
}


