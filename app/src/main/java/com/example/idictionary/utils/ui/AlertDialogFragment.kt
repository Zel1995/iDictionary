package com.example.idictionary.utils.ui

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment

class AlertDialogFragment : AppCompatDialogFragment() {
    companion object {
        private const val ALERT_TITLE_KEY = "ALERT_TITLE_KEY"
        private const val ALERT_MESSAGE_KEY = "ALERT_MESSAGE_KEY"
        fun newInstance(title: String?, message: String): AlertDialogFragment =
            AlertDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ALERT_TITLE_KEY, title)
                    putString(ALERT_MESSAGE_KEY, message)
                }
            }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val args = arguments
        var alertDialog = requireContext().getAlertDialog(null, null)
        if (args != null) {
            val title = args.getString(ALERT_TITLE_KEY)
            val message = args.getString(ALERT_MESSAGE_KEY)
            alertDialog = requireContext().getAlertDialog(title, message)
        }
        return alertDialog
    }

}