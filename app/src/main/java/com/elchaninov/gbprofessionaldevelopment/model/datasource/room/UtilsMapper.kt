package com.elchaninov.gbprofessionaldevelopment.model.datasource.room

import com.elchaninov.gbprofessionaldevelopment.model.data.DataModel
import com.elchaninov.gbprofessionaldevelopment.model.data.Meanings
import com.elchaninov.gbprofessionaldevelopment.model.data.Translation


fun map(dataModel: DataModel): EntityTranslation {
    return EntityTranslation(
        id = dataModel.id,
        text = dataModel.text
    )
}

fun map(meanings: Meanings, dataModel: DataModel): EntityMeaning {
    return EntityMeaning(
        id = meanings.id,
        text = meanings.translation?.translation,
        imageUrl = meanings.imageUrl,
        translationId = dataModel.id
    )
}

fun map(translationWhitsMeaning: TranslationWhitsMeaning): DataModel {
    return DataModel(
        id = translationWhitsMeaning.id,
        text = translationWhitsMeaning.text,
        meanings = translationWhitsMeaning.meanings?.map { map(it) }
    )
}

fun map(entityMeaning: EntityMeaning): Meanings {
    return Meanings(
        id = entityMeaning.id,
        imageUrl = entityMeaning.imageUrl,
        translation = Translation(entityMeaning.text)
    )
}


