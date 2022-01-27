package com.elchaninov.gbprofessionaldevelopment.model.datasource.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "TranslationTable")
data class TranslationEntity(
    @PrimaryKey
    @ColumnInfo
    @SerializedName("id")
    val id: String,

    @ColumnInfo
    @SerializedName("text")
    val text: String?,

    @ColumnInfo
    @SerializedName("translation")
    val translation: String? = null,

    @ColumnInfo
    @SerializedName("imageUrl")
    val imageUrl: String? = null,
)