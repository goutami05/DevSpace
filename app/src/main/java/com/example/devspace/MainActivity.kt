package com.example.devspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.devspace.datastore.ThemePreferences
import com.example.devspace.navigation.AppNavGraph
import com.example.devspace.navigation.BottomBar
import com.example.devspace.navigation.Screen
import com.example.devspace.ui.theme.DevSpaceTheme
import com.example.devspace.viewmodel.ThemeViewModel
import com.example.devspace.viewmodel.ThemeViewModelFactory
import androidx.compose.material3.Scaffold

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            val context = LocalContext.current

            val themePreferences = ThemePreferences(context)

            val themeViewModel: ThemeViewModel = viewModel(
                factory = ThemeViewModelFactory(themePreferences)
            )

            val isDarkTheme by themeViewModel.isDarkTheme.collectAsState()

            DevSpaceTheme(
                darkTheme = isDarkTheme
            ) {

                val navController = rememberNavController()

                val navBackStackEntry by navController.currentBackStackEntryAsState()

                val currentRoute = navBackStackEntry?.destination?.route

                val showBottomBar = currentRoute in listOf(
                    Screen.Home.route,
                    Screen.Repo.route,
                    Screen.Bookmark.route
                )

                Scaffold(

                    bottomBar = {

                        if (showBottomBar) {

                            BottomBar(navController)

                        }

                    }

                ) { innerPadding ->

                    AppNavGraph(

                        navController = navController,

                        modifier = Modifier.padding(innerPadding)

                    )

                }

            }

        }

    }

}