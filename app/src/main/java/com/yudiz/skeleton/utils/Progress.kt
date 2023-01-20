package com.yudiz.skeleton.utils

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.yudiz.skeleton.R

class Progress(context: Context) {

    private var dialog: Dialog
    private var isVisible: Boolean = false
    private var view: View

    init {
        dialog = Dialog(context)
        dialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        view = LayoutInflater.from(context).inflate(R.layout.custom_loading_dialog, LinearLayout(context), false)
        dialog.setContentView(view)
    }

    fun setCancelable(isCancelable: Boolean) {
        dialog.setCancelable(isCancelable)
    }

    fun hide() {
        if (dialog.isShowing) {
            dialog.dismiss()
            isVisible = false
        }
    }

    fun show() {
        if (!dialog.isShowing) {
            dialog.show()
            isVisible = true
        }
    }
}