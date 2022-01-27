package com.elchaninov.gbprofessionaldevelopment.model.datasource.room

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.elchaninov.gbprofessionaldevelopment.model.data.Meanings
import com.google.gson.annotations.SerializedName

data class TranslationWhitsMeaning(

    val id: Long,

    val text: String?,

    @Relation(parentColumn = "id", entityColumn = "translationId")
    val meanings: List<EntityMeaning>?
)