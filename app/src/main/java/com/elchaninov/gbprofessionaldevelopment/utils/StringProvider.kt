package com.elchaninov.gbprofessionaldevelopment.utils

import android.content.Context

class StringProvider(private var context: Context) {

    fun getStringResource(resourceId: Int): String{
        return context.getString(resourceId)
    }
}