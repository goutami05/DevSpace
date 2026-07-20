package com.example.devspace.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoBookmarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepoBookmark(repo: RepoBookmarkEntity)

    @Delete
    suspend fun deleteRepoBookmark(repo: RepoBookmarkEntity)

    @Query("DELETE FROM repo_bookmarks WHERE id = :id")
    suspend fun deleteRepoBookmarkById(id: Long)

    @Query("SELECT * FROM repo_bookmarks ORDER BY bookmarkedAt DESC")
    fun getAllRepoBookmarks(): Flow<List<RepoBookmarkEntity>>

    @Query("SELECT * FROM repo_bookmarks WHERE id = :id LIMIT 1")
    suspend fun getRepoBookmarkById(id: Long): RepoBookmarkEntity?

    @Query("SELECT EXISTS(SELECT 1 FROM repo_bookmarks WHERE id = :id LIMIT 1)")
    fun isRepoBookmarked(id: Long): Flow<Boolean>

    @Query("DELETE FROM repo_bookmarks")
    suspend fun clearAllRepoBookmarks()
}