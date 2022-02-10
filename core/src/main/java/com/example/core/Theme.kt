package com.example.core

import androidx.appcompat.app.AppCompatDelegate.*

enum class Theme(val value: Int) {
    LIGHT(MODE_NIGHT_NO),
    DARK(MODE_NIGHT_YES),
    AUTO(MODE_NIGHT_FOLLOW_SYSTEM);

    companion object {
        @JvmStatic
        fun fromName(status: String?): Theme? =
            values().find { value -> value.name == status }
    }
}