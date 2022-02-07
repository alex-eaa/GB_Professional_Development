package com.elchaninov.gbprofessionaldevelopment.model.usermodel

import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("text")
    val text: String?,

    @SerializedName("meanings")
    val meanings: List<Meanings>?,

    @SerializedName("favorite")
    val favorite: Boolean = false
)