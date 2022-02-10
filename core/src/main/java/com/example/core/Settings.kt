package com.example.core

import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Settings (context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)


    var nightTheme by prefs.theme(
        defaultValue = Theme.AUTO
    )

    private fun SharedPreferences.theme(
        defaultValue: Theme,
        key: (KProperty<*>) -> String = KProperty<*>::name
    ): ReadWriteProperty<Any, Theme?> =
        object : ReadWriteProperty<Any, Theme?> {
            override fun getValue(
                thisRef: Any,
                property: KProperty<*>
            ) = if (getString(key(property), defaultValue.name) != null) {
                Theme.fromName(getString(key(property), defaultValue.name))
            } else defaultValue

            override fun setValue(
                thisRef: Any,
                property: KProperty<*>,
                value: Theme?
            ) = edit().putString(key(property), value?.name).apply()
        }

    companion object {
        const val PREF_NAME = "settings"
    }
}