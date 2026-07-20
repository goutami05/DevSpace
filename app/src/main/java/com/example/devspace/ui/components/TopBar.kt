package com.example.devspace.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.devspace.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    subtitle: String,
    navController: NavController
) {

    Surface(
        color = MaterialTheme.colorScheme.background
    ) {

        Column {

            CenterAlignedTopAppBar(

                title = {

                    Column {

                        Text(
                            text = title,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = subtitle,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                    }

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

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                color = MaterialTheme.colorScheme.outlineVariant
            )

        }

    }

}