package com.example.devspace.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.devspace.model.github.Owner
import com.example.devspace.model.github.Repo

@Entity(tableName = "repo_bookmarks")
data class RepoBookmarkEntity(

    @PrimaryKey
    val id: Long,

    val name: String,

    val description: String?,

    val language: String?,

    val stars: Int,

    val forks: Int,

    val owner: String,

    val ownerAvatar: String,

    val repoUrl: String,

    val bookmarkedAt: Long = System.currentTimeMillis()

)

fun Repo.toRepoBookmarkEntity(): RepoBookmarkEntity {

    return RepoBookmarkEntity(
        id = id.toLong(),
        name = name,
        description = description,
        language = language,
        stars = stargazers_count,
        forks = forks_count,
        owner = owner.login,
        ownerAvatar = owner.avatar_url,
        repoUrl = html_url
    )
}

fun RepoBookmarkEntity.toRepo(): Repo {

    return Repo(
        id = id.toInt(),
        name = name,
        full_name = "$owner/$name",
        description = description,
        html_url = repoUrl,
        stargazers_count = stars,
        forks_count = forks,
        owner = Owner(
            login = owner,
            avatar_url = ownerAvatar
        ),
        language = language
    )
}