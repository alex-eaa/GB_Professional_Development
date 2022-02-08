package com.elchaninov.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TranslationTable")
data class EntityTranslation(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long?,

    @ColumnInfo(name = "text")
    val text: String?,

    @ColumnInfo(name = "favorite")
    val favorite: Boolean,
)