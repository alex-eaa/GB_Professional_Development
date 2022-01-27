package com.elchaninov.gbprofessionaldevelopment.model.datasource.room

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.elchaninov.gbprofessionaldevelopment.model.data.Meanings
import com.elchaninov.gbprofessionaldevelopment.model.data.Translation
import com.google.gson.annotations.SerializedName

@Entity(tableName = "TranslationTable")
data class EntityTranslation(
    @PrimaryKey
    @ColumnInfo
    @SerializedName("id")
    val id: Long?,

    @ColumnInfo
    @SerializedName("text")
    val text: String?,
)