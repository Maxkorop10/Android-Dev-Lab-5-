package com.example.lab_5.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.lab_5.models.AuthorData

@Dao
interface MyAuthorsDao {
    @Insert
    suspend fun insertAuthor(author: AuthorData) : Long

    @Insert
    suspend fun insertAuthors(authors: List<AuthorData>)

    @Update
    suspend fun updateAuthor(author: AuthorData)

    @Query("SELECT * FROM authors")
    suspend fun getAllAuthors(): List<AuthorData>

    @Delete
    suspend fun deleteAuthor(book: AuthorData)

    @Query("DELETE FROM authors")
    suspend fun deleteAllAuthors()
}