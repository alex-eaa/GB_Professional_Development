package com.elchaninov.gbprofessionaldevelopment.model.datasource.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "TranslationTable")
data class EntityTranslation(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long?,

    @ColumnInfo(name = "text")
    val text: String?,
)