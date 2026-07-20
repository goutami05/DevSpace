package com.example.devspace.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.devspace.utils.Constants

@Database(
    entities = [
        BookmarkEntity::class,
        RepoBookmarkEntity::class
    ],
    version = 3,
    exportSchema = false
)
abstract class BookmarkDatabase : RoomDatabase() {

    abstract fun bookmarkDao(): BookmarkDao

    abstract fun repoBookmarkDao(): RepoBookmarkDao

    companion object {

        @Volatile
        private var INSTANCE: BookmarkDatabase? = null

        fun getDatabase(context: Context): BookmarkDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookmarkDatabase::class.java,
                    Constants.DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance

                instance
            }
        }
    }
}


