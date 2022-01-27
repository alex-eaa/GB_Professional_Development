package com.elchaninov.gbprofessionaldevelopment.model.data

import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("text")
    val text: String?,
    @SerializedName("meanings")
    val meanings: List<Meanings>?
)