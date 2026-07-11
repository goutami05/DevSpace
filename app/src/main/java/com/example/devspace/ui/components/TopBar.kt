package com.example.devspace.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    subtitle: String? = null
) {

    CenterAlignedTopAppBar(

        title = {

            Column {

                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                if (subtitle != null) {

                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall
                    )

                }

            }

        }

    )

}