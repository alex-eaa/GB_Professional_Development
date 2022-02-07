package com.elchaninov.gbprofessionaldevelopment.model.dto

import com.google.gson.annotations.SerializedName

data class MeaningsDto(
    @SerializedName("id")
    val id: Long?,

    @SerializedName("translation")
    val translation: TranslationDto?,

    @SerializedName("imageUrl")
    val imageUrl: String?
)