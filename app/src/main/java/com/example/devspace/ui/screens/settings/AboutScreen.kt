package com.example.devspace.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    navController: NavController
) {

    Scaffold(

        topBar = {

            CenterAlignedTopAppBar(

                title = {

                    Text("About DevSpace")

                },

                navigationIcon = {

                    IconButton(

                        onClick = {

                            navController.popBackStack()

                        }

                    ) {

                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )

                    }

                }

            )

        }

    ) { padding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),

            horizontalAlignment = Alignment.CenterHorizontally,

            verticalArrangement = Arrangement.Center

        ) {

            Icon(

                imageVector = Icons.Default.Info,

                contentDescription = null,

                tint = MaterialTheme.colorScheme.primary,

                modifier = Modifier.height(80.dp)

            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(

                text = "DevSpace",

                style = MaterialTheme.typography.headlineMedium,

                fontWeight = FontWeight.Bold

            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(

                text = "Version 1.0",

                style = MaterialTheme.typography.titleMedium

            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(

                text = "DevSpace is a modern Android application that helps developers stay updated with the latest technology news and discover trending GitHub repositories.",

                style = MaterialTheme.typography.bodyLarge

            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(

                text = "Features",

                style = MaterialTheme.typography.titleLarge,

                fontWeight = FontWeight.Bold

            )

            Spacer(modifier = Modifier.height(12.dp))

            Text("• Latest Tech News")

            Text("• Trending GitHub Repositories")

            Text("• Bookmark News")

            Text("• Bookmark Repositories")

            Text("• Light & Dark Theme")

        }

    }

}