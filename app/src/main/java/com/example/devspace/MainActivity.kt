package com.example.devspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.devspace.ui.components.EmptyView
import com.example.devspace.ui.components.ErrorView
import com.example.devspace.ui.components.LoadingView
import com.example.devspace.ui.components.NewsCard
import com.example.devspace.ui.components.RepoCard
import com.example.devspace.ui.components.SearchBar
import com.example.devspace.ui.screens.splash.SplashScreen
import com.example.devspace.ui.theme.DevSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                SplashScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DevSpaceTheme {
        Greeting("Android")
    }
}