package com.example.lab_5.Repo

import com.example.lab_5.Dao.MyAuthorsDao
import com.example.lab_5.Dao.MyLibraryDao
import com.example.lab_5.api.MyApi
import com.example.lab_5.api.MyRetrofitClient
import com.example.lab_5.models.AuthorData
import com.example.lab_5.models.LibraryData

class MyRepo(private val authorDao: MyAuthorsDao, private val bookDao: MyLibraryDao) {

    private val myRetrofitClient = MyRetrofitClient.getClient()
    private val myApi = myRetrofitClient.create(MyApi::class.java)

    suspend fun loadAuthors(): List<AuthorData>? {
        val response = myApi.getAuthors()
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    suspend fun loadBooks(): List<LibraryData>? {
        val response = myApi.getBooks()
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }


    suspend fun insertAuthor(author: AuthorData) {
        authorDao.insertAuthor(author)
    }

    suspend fun insertAuthors(authors: List<AuthorData>) {
        authorDao.insertAuthors(authors)
    }

    suspend fun insertBook(book: LibraryData) {
        bookDao.insertBook(book)
    }

    suspend fun insertBooks(books: List<LibraryData>) {
        bookDao.insertBooks(books)
    }

    suspend fun updateAuthor(author: AuthorData) {
        authorDao.updateAuthor(author)
    }

    suspend fun updateBook(book: LibraryData) {
        bookDao.updateBook(book)
    }

    suspend fun getAllAuthors(): List<AuthorData> {
        return authorDao.getAllAuthors()
    }

    suspend fun getAllBooks(): List<LibraryData> {
        return bookDao.getAllBooks()
    }

    suspend fun deleteAuthor(author: AuthorData) {
        authorDao.deleteAuthor(author)
    }

    suspend fun deleteBook(book: LibraryData) {
        bookDao.deleteBook(book)
    }

    suspend fun deleteAllAuthors() {
        authorDao.deleteAllAuthors()
    }

    suspend fun deleteAllBooks() {
        bookDao.deleteAllBooks()
    }
}