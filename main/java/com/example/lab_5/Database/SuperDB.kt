package com.example.lab_5.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lab_5.Dao.MyAuthorsDao
import com.example.lab_5.Dao.MyLibraryDao
import com.example.lab_5.models.AuthorData
import com.example.lab_5.models.LibraryData

@Database(
    entities = [
        LibraryData::class,
        AuthorData::class,
    ],
    version = 2,
    exportSchema = false
)

abstract class SuperDB : RoomDatabase() {
    abstract fun authorDao(): MyAuthorsDao
    abstract fun bookDao(): MyLibraryDao

    companion object {
        @Volatile
        private var INSTANCE: SuperDB? = null

        fun getDatabase(context: Context): SuperDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    SuperDB::class.java,
                    "super_data"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}