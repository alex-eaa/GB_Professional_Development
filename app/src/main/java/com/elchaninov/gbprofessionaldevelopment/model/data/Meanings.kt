package com.elchaninov.gbprofessionaldevelopment.model.data

import com.google.gson.annotations.SerializedName

data class Meanings(
    @SerializedName("id")
    val id: Long?,

    @SerializedName("translation")
    val translation: Translation?,

    @SerializedName("imageUrl")
    val imageUrl: String?
)