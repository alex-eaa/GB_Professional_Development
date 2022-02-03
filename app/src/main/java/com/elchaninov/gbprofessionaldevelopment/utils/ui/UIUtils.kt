package com.elchaninov.gbprofessionaldevelopment.utils.ui

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.elchaninov.gbprofessionaldevelopment.R

fun getStubAlertDialog(context: Context): AlertDialog {
    return getAlertDialog(context, null, null, null, null)
}

fun getAlertDialog(
    context: Context,
    title: String?,
    message: String?,
    buttonTitle: String?,
    onSearchClickListener: AlertDialogFragment.OnActionButtonClickListener?,
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
    if (onSearchClickListener != null && !buttonTitle.isNullOrBlank()) {
        builder.setPositiveButton(buttonTitle) { dialog, _ ->
            onSearchClickListener.onClickActionButton()
            dialog.dismiss()
        }
    }
    builder.setNegativeButton(R.string.dialog_button_cancel) { dialog, _ -> dialog.dismiss() }
    builder.setCancelable(true)
    return builder.create()
}
