package com.elchaninov.gbprofessionaldevelopment.model.data

import com.google.gson.annotations.SerializedName

data class Translation(
    @SerializedName("text")
    val translation: String?
)