package com.elchaninov.gbprofessionaldevelopment.model.datasource.room

import androidx.room.Relation

data class TranslationWhitsMeaning(

    val id: Long,

    val text: String?,

    @Relation(parentColumn = "id", entityColumn = "translationId")
    val meanings: List<EntityMeaning>?
)