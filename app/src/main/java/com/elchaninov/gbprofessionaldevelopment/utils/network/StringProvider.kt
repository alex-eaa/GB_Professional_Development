package com.elchaninov.gbprofessionaldevelopment.utils.network

import android.content.Context

class StringProvider(private var context: Context) {

    fun getStringResource(resourceId: Int): String{
        return context.getString(resourceId)
    }
}