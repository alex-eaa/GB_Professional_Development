package com.elchaninov.gbprofessionaldevelopment.model.dto

import com.google.gson.annotations.SerializedName

data class DataModelDto(
    @SerializedName("id")
    val id: Long,

    @SerializedName("text")
    val text: String?,

    @SerializedName("meanings")
    val meanings: List<MeaningsDto>?
)