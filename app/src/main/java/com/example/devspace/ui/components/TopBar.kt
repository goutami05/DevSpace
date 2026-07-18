package com.example.devspace.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.devspace.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(

    title: String,

    navController: NavController,
    subtitle: String

) {

    CenterAlignedTopAppBar(

        title = {

            Text(

                text = title,

                style = MaterialTheme.typography.titleLarge

            )

        },

        actions = {

            IconButton(

                onClick = {

                    navController.navigate(Screen.Settings.route)

                }

            ) {

                Icon(

                    imageVector = Icons.Default.Settings,

                    contentDescription = "Settings"

                )

            }

        }

    )

}