package com.elchaninov.gbprofessionaldevelopment.model.data

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class DataModel(
    @PrimaryKey
    @SerializedName("id")
    val id: Long,

    @SerializedName("text")
    val text: String?,

    @SerializedName("meanings")
    val meanings: List<Meanings>?
)