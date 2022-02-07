package com.elchaninov.gbprofessionaldevelopment.model.usermodel

import com.google.gson.annotations.SerializedName

data class Meanings(
    @SerializedName("translation")
    val translation: Translation?,

    @SerializedName("imageUrl")
    val imageUrl: String?
)