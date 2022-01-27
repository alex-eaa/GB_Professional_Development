package com.elchaninov.gbprofessionaldevelopment.model.datasource.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "MeaningTable",
    foreignKeys = [
        ForeignKey(
            entity = EntityTranslation::class,
            parentColumns = ["id"],
            childColumns = ["translationId"],
            onDelete = CASCADE)])
data class EntityMeaning(
    @PrimaryKey
    @SerializedName("id")
    val id: Long?,

    @SerializedName("text")
    val text: String?,

    @SerializedName("imageUrl")
    val imageUrl: String? = null,

    @ColumnInfo(name = "translationId")
    val translationId: Long?,
)