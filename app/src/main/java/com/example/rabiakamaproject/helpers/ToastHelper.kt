package com.example.rabiakamaproject.helpers

import android.content.Context
import android.widget.Toast

class ToastHelper(private val mContext: Context) {

    private var toast: Toast? = null

    /**
     * Shows short toast while if there is one cancels it first
     *
     * @param message - Text will be showed on toast
     */
    fun showShort(message: String) {
        toast?.cancel()
        toast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT)
        toast?.show()
    }

    /**
     * Shows long toast while if there is one cancels it first
     *
     * @param message - Text will be showed on toast
     */
    fun showLong(message: String) {
        toast?.cancel()
        toast = Toast.makeText(mContext, message, Toast.LENGTH_LONG)
        toast?.show()
    }
}