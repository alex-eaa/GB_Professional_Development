package com.elchaninov.gbprofessionaldevelopment.model.datasource.room

import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.model.data.Meanings
import com.elchaninov.gbprofessionaldevelopment.model.data.Translation


fun DataModel.toEntityTranslation(): EntityTranslation = EntityTranslation(
    id = this.id,
    text = this.text
)

fun TranslationWhitsMeaning.toDataModel(): DataModel = DataModel(
    id = this.id,
    text = this.text,
    meanings = this.meanings?.map { entityMeaning -> entityMeaning.toMeanings() }
)

fun EntityMeaning.toMeanings(): Meanings = Meanings(
    id = this.id,
    imageUrl = this.imageUrl,
    translation = Translation(this.text)
)

fun map(meanings: Meanings, dataModel: DataModel): EntityMeaning {
    return EntityMeaning(
        id = meanings.id,
        text = meanings.translation?.translation,
        imageUrl = meanings.imageUrl,
        translationId = dataModel.id
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


