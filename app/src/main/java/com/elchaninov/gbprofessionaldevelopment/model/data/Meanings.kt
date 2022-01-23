package com.elchaninov.gbprofessionaldevelopment.model.data

import com.google.gson.annotations.SerializedName

data class Meanings(
    @SerializedName("translation")
    val translation: Translation?,
    @SerializedName("imageUrl")
    val imageUrl: String?
)