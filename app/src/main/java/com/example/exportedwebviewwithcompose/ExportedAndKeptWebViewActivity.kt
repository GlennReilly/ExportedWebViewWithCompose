package com.example.exportedwebviewwithcompose

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.exportedwebviewwithcompose.ui.theme.ExportedWebViewWithComposeTheme

class ExportedAndKeptWebViewActivity : ComponentActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExportedWebViewWithComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val url = intent.getStringExtra("url")
                    WebViewScreen3(url = url ?: "")
                }
            }
        }
    }
}

@Composable
fun WebViewScreen3(url: String) {
    AndroidView(factory = { context ->
        WebView(context).apply {
            webViewClient = WebViewClient()

            // Enable JavaScript
            settings.javaScriptEnabled = true

            // Add a JavaScript interface
            addJavascriptInterface(WebAppInterface(context), "Android")

            // Load the URL
            loadUrl(url)
        }
    }, update = { webView ->
        webView.loadUrl(url)
    })
}