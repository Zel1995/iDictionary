package com.example.utils

import android.app.AlertDialog
import android.content.Context


fun Context.getAlertDialog(title: String?, message: String?):AlertDialog {
    val builder = AlertDialog.Builder(this)
    var finalTitle = getString(R.string.dialog_title)
    if (!title.isNullOrBlank()) {
        finalTitle = title
    }
    builder.setTitle(finalTitle)
    if (!message.isNullOrBlank()) {
        builder.setMessage(message)
    }
    builder.setCancelable(true)
    builder.setNegativeButton("cancel") { dialog, _ -> dialog.dismiss() }
    return builder.create()
}