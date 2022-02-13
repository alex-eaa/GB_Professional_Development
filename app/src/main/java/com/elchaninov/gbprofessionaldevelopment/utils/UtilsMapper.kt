package com.elchaninov.gbprofessionaldevelopment.utils

import com.elchaninov.gbprofessionaldevelopment.model.data.*
import com.elchaninov.gbprofessionaldevelopment.model.datasource.room.EntityMeaning
import com.elchaninov.gbprofessionaldevelopment.model.datasource.room.EntityTranslation
import com.elchaninov.gbprofessionaldevelopment.model.datasource.room.TranslationWhitsMeaning


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

fun TranslationWhitsMeaning.toDataModel(): DataModel = DataModel(
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

fun convertMeaningsToString(meanings: List<Meanings>?): String {
    var meaningsSeparatedByComma = String()
    meanings?.forEachIndexed { index, meaning ->
        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
            String.format("%s%s", meaning.translation?.translation, ", ")
        } else {
            meaning.translation?.translation
        }
    }
    return meaningsSeparatedByComma
}


