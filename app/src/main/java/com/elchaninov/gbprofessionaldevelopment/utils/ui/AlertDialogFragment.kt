package com.elchaninov.gbprofessionaldevelopment.utils.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDialogFragment

class AlertDialogFragment : AppCompatDialogFragment() {

    private var onSearchClickListener: OnActionButtonClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnActionButtonClickListener) onSearchClickListener = context
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = activity
        var alertDialog = getStubAlertDialog(context!!)
        val args = arguments
        if (args != null) {
            val title = args.getString(TITLE_EXTRA)
            val message = args.getString(MESSAGE_EXTRA)
            val buttonTitle = args.getString(TITLE_ACTION_BUTTON_EXTRA)
            alertDialog =
                getAlertDialog(context, title, message, buttonTitle, onSearchClickListener)
        }
        return alertDialog
    }

    override fun onDestroyView() {
        onSearchClickListener = null
        super.onDestroyView()
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
            buttonTitle: String? = null
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
