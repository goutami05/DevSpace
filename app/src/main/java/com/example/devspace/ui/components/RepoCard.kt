package com.example.devspace.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CallSplit
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.devspace.model.github.Repo

@Composable
fun RepoCard(
    repo: Repo,
    isBookmarked: Boolean,
    onBookmarkClick: () -> Unit,
    onRepoClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .animateContentSize()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onRepoClick()
            },

        shape = RoundedCornerShape(20.dp),

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )

    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
                .animateContentSize()
        ) {

            Text(
                text = repo.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = repo.description ?: "",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 3
            )

            Spacer(modifier = Modifier.height(14.dp))

            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
            ) {

                Text(
                    text = repo.language ?: "Unknown",
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 5.dp
                    ),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelMedium
                )

            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = repo.owner.login,
                    style = MaterialTheme.typography.labelLarge
                )

            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = repo.stargazers_count.toString()
                    )

                }

                Spacer(modifier = Modifier.width(20.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.CallSplit,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = repo.forks_count.toString()
                    )

                }

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = onBookmarkClick
                ) {

                    Icon(
                        imageVector =
                            if (isBookmarked)
                                Icons.Filled.Bookmark
                            else
                                Icons.Outlined.BookmarkBorder,

                        contentDescription = "Bookmark Repository",
                        tint = MaterialTheme.colorScheme.primary
                    )

                }

                IconButton(
                    onClick = onRepoClick
                ) {

                    Icon(
                        imageVector = Icons.Default.OpenInNew,
                        contentDescription = "Open Repository",
                        tint = MaterialTheme.colorScheme.primary
                    )

                }

            }

        }

    }

}