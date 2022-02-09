package com.elchaninov.model.usermodel

data class DataModel(
    val text: String?,
    val meanings: List<Meanings>?,
    val favorite: Boolean = false
)