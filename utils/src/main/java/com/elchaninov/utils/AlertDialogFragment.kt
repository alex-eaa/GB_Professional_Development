package com.elchaninov.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment

class AlertDialogFragment : AppCompatDialogFragment() {

    private var onActionButtonClickListener: OnActionButtonClickListener? = null
    private var allowAction = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnActionButtonClickListener) onActionButtonClickListener = context
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = requireContext()
        var alertDialog = getStubAlertDialog(context)
        arguments?.let { bundle ->
            val title = bundle.getString(TITLE_EXTRA)
            val message = bundle.getString(MESSAGE_EXTRA)
            val buttonTitle = bundle.getString(TITLE_ACTION_BUTTON_EXTRA)
            alertDialog =
                getAlertDialog(context, title, message, buttonTitle)
        }
        return alertDialog
    }

    private fun getStubAlertDialog(context: Context): AlertDialog {
        return getAlertDialog(context, null, null, null)
    }

    private fun getAlertDialog(
        context: Context,
        title: String?,
        message: String?,
        buttonTitle: String?,
    ): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(
            if (title.isNullOrBlank()) context.getString(R.string.dialog_title_stub) else title
        )
        builder.setMessage(
            if (message.isNullOrBlank()) null else message
        )
        if (!buttonTitle.isNullOrBlank()) {
            builder.setPositiveButton(buttonTitle) { dialog, _ ->
                allowAction = true
                dialog.dismiss()
            }
        }
        builder.setNegativeButton(R.string.dialog_button_cancel) { dialog, _ -> dialog.dismiss() }
        builder.setCancelable(true)
        return builder.create()
    }

    override fun onDetach() {
        if (allowAction) onActionButtonClickListener?.onClickActionButton()
        onActionButtonClickListener = null
        super.onDetach()
    }

    interface OnActionButtonClickListener {
        fun onClickActionButton()
    }

    companion object {

        private const val TITLE_EXTRA = "89cbce59-e28f-418f-b470-ff67125c2e2f"
        private const val MESSAGE_EXTRA = "0dd00b66-91c2-447d-b627-530065040905"
        private const val TITLE_ACTION_BUTTON_EXTRA = "1dd00b66-91c2-447d-b627-530065040905"

        fun newInstance(
            title: String?,
            message: String?,
            buttonTitle: String? = null,
        ): AlertDialogFragment {
            val dialogFragment = AlertDialogFragment()
            val args = Bundle()
            args.putString(TITLE_EXTRA, title)
            args.putString(MESSAGE_EXTRA, message)
            args.putString(TITLE_ACTION_BUTTON_EXTRA, buttonTitle)
            dialogFragment.arguments = args
            return dialogFragment
        }
    }
}
