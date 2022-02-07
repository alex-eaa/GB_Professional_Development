package com.elchaninov.gbprofessionaldevelopment.model.usermodel

import com.google.gson.annotations.SerializedName

data class Translation(
    @SerializedName("text")
    val translation: String?
)