package com.example.devspace.repository

import com.example.devspace.database.RepoBookmarkDao
import com.example.devspace.database.RepoBookmarkEntity
import com.example.devspace.database.toRepo
import com.example.devspace.database.toRepoBookmarkEntity
import com.example.devspace.model.github.Repo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepoBookmarkRepository(
    private val repoBookmarkDao: RepoBookmarkDao
) {

    val allRepoBookmarks: Flow<List<Repo>> =
        repoBookmarkDao.getAllRepoBookmarks()
            .map { entities ->
                entities.map(RepoBookmarkEntity::toRepo)
            }

    suspend fun addBookmark(repo: Repo) {
        repoBookmarkDao.insertRepoBookmark(repo.toRepoBookmarkEntity())
    }

    suspend fun removeBookmark(repo: Repo) {
        repoBookmarkDao.deleteRepoBookmarkById(repo.id.toLong())
    }

    suspend fun toggleBookmark(repo: Repo) {
        if (repoBookmarkDao.getRepoBookmarkById(repo.id.toLong()) != null) {
            repoBookmarkDao.deleteRepoBookmarkById(repo.id.toLong())
        } else {
            addBookmark(repo)
        }
    }

    fun isBookmarked(id: Long): Flow<Boolean> =
        repoBookmarkDao.isRepoBookmarked(id)

    suspend fun clearAllBookmarks() =
        repoBookmarkDao.clearAllRepoBookmarks()
}