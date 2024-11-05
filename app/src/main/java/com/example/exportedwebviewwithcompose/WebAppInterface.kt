package com.example.exportedwebviewwithcompose

import android.webkit.JavascriptInterface
import android.content.Context
import android.util.Log
import android.widget.Toast

class WebAppInterface(private val mContext: Context) {

    /** Show a toast from the web page  */
    @JavascriptInterface
    fun showToast(toast: String) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
        Log.d("WebAppInterface", "showToast: $toast")
    }
}