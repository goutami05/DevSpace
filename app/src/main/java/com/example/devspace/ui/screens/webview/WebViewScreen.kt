package com.example.devspace.ui.screens.webview

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebViewScreen(
    url: String,
    navController: NavController
) {

    Scaffold(

        topBar = {

            CenterAlignedTopAppBar(

                title = {
                    Text(text = "Article")
                },

                navigationIcon = {

                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {

                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )

                    }

                }

            )

        }

    ) { padding ->

        AndroidView(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding),

            factory = { context ->

                WebView(context).apply {

                    webViewClient = WebViewClient()

                    settings.javaScriptEnabled = true

                    settings.domStorageEnabled = true

                    loadUrl(url)

                }

            }

        )

    }

}