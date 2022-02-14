package com.elchaninov.model.usermodel

data class DataModel(
    val text: String = "",
    val meanings: List<Meanings> = listOf(),
    val favorite: Boolean = false
)