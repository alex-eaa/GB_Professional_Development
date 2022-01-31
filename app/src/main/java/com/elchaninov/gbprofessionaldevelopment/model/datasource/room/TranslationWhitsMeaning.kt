package com.elchaninov.gbprofessionaldevelopment.model.datasource.room

import androidx.room.ColumnInfo
import androidx.room.Relation

data class TranslationWhitsMeaning(
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "text")
    val text: String?,

    @Relation(parentColumn = "id", entityColumn = "translationId")
    val meanings: List<EntityMeaning>?
)