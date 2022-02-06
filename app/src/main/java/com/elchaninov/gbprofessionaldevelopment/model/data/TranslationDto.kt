package com.elchaninov.gbprofessionaldevelopment.model.data

import com.google.gson.annotations.SerializedName

data class TranslationDto(
    @SerializedName("text")
    val translation: String?
)