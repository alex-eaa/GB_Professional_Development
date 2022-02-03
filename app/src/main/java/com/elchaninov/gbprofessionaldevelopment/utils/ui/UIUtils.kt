package com.elchaninov.gbprofessionaldevelopment.utils.ui

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.elchaninov.gbprofessionaldevelopment.R

fun getStubAlertDialog(context: Context): AlertDialog {
    return getAlertDialog(context, null, null)
}

fun getAlertDialog(context: Context, title: String?, message: String?): AlertDialog {
    val builder = AlertDialog.Builder(context)
    var finalTitle: String? = context.getString(R.string.dialog_title_stub)
    if (!title.isNullOrBlank()) {
        finalTitle = title
    }
    builder.setTitle(finalTitle)
    if (!message.isNullOrBlank()) {
        builder.setMessage(message)
    }
    builder.setCancelable(true)
    builder.setPositiveButton(R.string.dialog_button_cancel) { dialog, _ -> dialog.dismiss() }
    return builder.create()
}

fun getAlertDialogWithTrayAgain(
    context: Context,
    title: String?,
    message: String?,
    block: () -> Unit,
): AlertDialog {
    val builder = AlertDialog.Builder(context)
    builder.setTitle(
        if (title.isNullOrBlank()) context.getString(R.string.dialog_title_stub)
        else title
    )
    builder.setMessage(
        if (message.isNullOrBlank()) null
        else message
    )
    builder.setCancelable(true)
    builder.setNegativeButton(R.string.dialog_button_try_again) { dialog, _ ->
        block()
        dialog.dismiss()
    }
    builder.setPositiveButton(R.string.dialog_button_cancel) { dialog, _ -> dialog.dismiss() }
    return builder.create()
}
