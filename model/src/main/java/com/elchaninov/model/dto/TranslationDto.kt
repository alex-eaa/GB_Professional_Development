package com.elchaninov.model.dto

import com.google.gson.annotations.SerializedName

data class TranslationDto(
    @SerializedName("text")
    val translation: String?
)