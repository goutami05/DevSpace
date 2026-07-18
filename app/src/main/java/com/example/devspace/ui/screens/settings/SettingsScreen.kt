package com.example.devspace.ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.devspace.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController
) {

    var darkTheme by remember {
        mutableStateOf(false)
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text("Settings")

                }

            )

        }

    ) { padding ->

        LazyColumn(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding),

            verticalArrangement = Arrangement.spacedBy(16.dp),

            contentPadding = PaddingValues(16.dp)

        ) {

            item {

                Card {

                    ListItem(

                        headlineContent = {

                            Text("Dark Theme")

                        },

                        supportingContent = {

                            Text("Switch between Light and Dark mode")

                        },

                        leadingContent = {

                            androidx.compose.material3.Icon(
                                imageVector = Icons.Default.Palette,
                                contentDescription = null
                            )

                        },

                        trailingContent = {

                            Switch(
                                checked = darkTheme,
                                onCheckedChange = {
                                    darkTheme = it
                                }
                            )

                        }

                    )

                }

            }

            item {

                Card(

                    modifier = Modifier.clickable {

                        navController.navigate(Screen.About.route)

                    }

                ) {

                    ListItem(

                        headlineContent = {

                            Text("About DevSpace")

                        },

                        supportingContent = {

                            Text("Learn more about the application")

                        },

                        leadingContent = {

                            androidx.compose.material3.Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null
                            )

                        },

                        trailingContent = {

                            androidx.compose.material3.Icon(
                                imageVector = Icons.Default.ChevronRight,
                                contentDescription = null
                            )

                        }

                    )

                }

            }

        }

    }

}