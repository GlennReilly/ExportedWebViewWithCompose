package com.example.exportedwebviewwithcompose

import android.webkit.JavascriptInterface
import android.content.Context
import android.util.Log
import android.widget.Toast

class WebAppInterface(private val mContext: Context) {

    @JavascriptInterface
    @Suppress("Unused")
    fun showToast() {
        Toast.makeText(mContext, "toast", Toast.LENGTH_SHORT).show()
        Log.d("WebAppInterface", "showToast: toast!")
    }

    @Suppress("Unused")
    fun showMessageToast(toast: String) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
        Log.d("WebAppInterface", "showToast: $toast")
    }

    @Suppress("Unused")
    fun insertData(data: String) {
        showMessageToast("insertData: $data")
        Log.d("WebAppInterface", "insertData: $data")
    }
}
