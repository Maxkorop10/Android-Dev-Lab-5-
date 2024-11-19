package com.example.lab_5.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.lab_5.models.LibraryData

@Dao
interface MyLibraryDao {
    @Insert
    suspend fun insertBook(book: LibraryData) : Long

    @Insert
    suspend fun insertBooks(books: List<LibraryData>)

    @Update
    suspend fun updateBook(book: LibraryData)

    @Query("SELECT * FROM books")
    suspend fun getAllBooks(): List<LibraryData>

    @Delete
    suspend fun deleteBook(book: LibraryData)

    @Query("DELETE FROM books")
    suspend fun deleteAllBooks()
}