package com.example.exportedwebviewwithcompose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.exportedwebviewwithcompose.ui.theme.ExportedWebViewWithComposeTheme
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExportedWebViewWithComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val viewModel = viewModel<MainViewModel>()
    val destinationURL = viewModel.getDestinationURL()
    val destinationURL2 = viewModel.getDestinationURL2()
    val destinationURL3 = viewModel.getDestinationURL3()
    val context: Context = LocalContext.current

    writeToEncryptedSharedPrefs(context, destinationURL)
    writeToStandardSharedPrefs(context, destinationURL)

    writeToEncryptedSharedPrefs(context, destinationURL2)
    writeToStandardSharedPrefs(context, destinationURL2)

    writeToEncryptedSharedPrefs(context, destinationURL3)
    writeToStandardSharedPrefs(context, destinationURL3)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        Button(onClick = {
            val intent = Intent(context, ExportedWebViewActivity::class.java)
            intent.putExtra("url", destinationURL)
            context.startActivity(intent)
        }) {
            Text("Open ExportedWebViewActivity")
        }

        Button(onClick = {
            val intent = Intent(context, NotExportedWebViewActivity::class.java)
            intent.putExtra("url", destinationURL2)
            context.startActivity(intent)
        }) {
            Text("Open NotExportedWebViewActivity")
        }

        Button(onClick = {
            val intent = Intent(context, ExportedAndKeptWebViewActivity::class.java)
            intent.putExtra("url", destinationURL3)
            context.startActivity(intent)
        }) {
            Text("Open ExportedAndKeptWebViewActivity")
        }
    }
}

@Composable
private fun writeToStandardSharedPrefs(context: Context, destinationURL: String) {
    val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString("destinationURL", destinationURL)
    editor.apply()
}

@Composable
private fun writeToEncryptedSharedPrefs(context: Context, destinationURL: String) {
    val masterKeyAlias = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()
    val encryptedSharedPreferences = EncryptedSharedPreferences.create(
        context,
        "my_encrypted_prefs",
        masterKeyAlias,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
    val encryptedSharedPreferencesEditor = encryptedSharedPreferences.edit()
    encryptedSharedPreferencesEditor.putString("destinationURL", destinationURL)
    encryptedSharedPreferencesEditor.apply()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExportedWebViewWithComposeTheme {
        MainScreen()
    }
}