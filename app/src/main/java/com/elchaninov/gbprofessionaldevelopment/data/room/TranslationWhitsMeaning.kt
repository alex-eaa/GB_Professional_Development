package com.elchaninov.gbprofessionaldevelopment.data.room

import androidx.room.ColumnInfo
import androidx.room.Relation

data class TranslationWhitsMeaning(
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "text")
    val text: String?,

    @ColumnInfo(name = "favorite")
    val favorite: Boolean,

    @Relation(parentColumn = "id", entityColumn = "translationId")
    val meanings: List<EntityMeaning>?
)