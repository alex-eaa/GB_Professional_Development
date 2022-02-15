package com.elchaninov.utils

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.view.View

fun View.enableBlurEffect() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val blurEffect =
            RenderEffect.createBlurEffect(12f, 12f, Shader.TileMode.MIRROR)
        this.setRenderEffect(blurEffect)
    }
}

fun View.disableBlurEffect() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        this.setRenderEffect(null)
    }
}