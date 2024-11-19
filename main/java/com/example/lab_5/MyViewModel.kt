package com.example.lab_5

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lab_5.models.AuthorData
import com.example.lab_5.models.InterfaceItem
import com.example.lab_5.models.LibraryData
import kotlinx.coroutines.launch

class MyViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = (application as MyApp).repo

    private var my_list: MutableLiveData<List<InterfaceItem>> =
        MutableLiveData<List<InterfaceItem>>().apply {
            value = emptyList()
        }
    val items: LiveData<List<InterfaceItem>> = my_list

    init {
        viewModelScope.launch {
//            repo.deleteAllAuthors()
//            repo.deleteAllBooks()

            val authors = repo.loadAuthors()
            authors?.let { repo.insertAuthors(authors) }

            val books = repo.loadBooks()
            books?.let { repo.insertBooks(books) }

            get_list()
        }
    }

    fun get_list(){
        viewModelScope.launch {
            val authors = repo.getAllAuthors()
            val books = repo.getAllBooks()

            val groupedItems = mutableListOf<InterfaceItem>()
            for (author in authors) {
                groupedItems.add(author)
                groupedItems.addAll(books.filter { it.authorID == author.id })
            }

            my_list.postValue(groupedItems)
        }
    }

    fun addAuthor(author: AuthorData) {
        viewModelScope.launch {
            repo.insertAuthor(author)
            get_list()
        }
    }

    fun addBook(book: LibraryData) {
        viewModelScope.launch {
            repo.insertBook(book)
            get_list()
        }
    }

    fun updateAuthor(author: AuthorData) {
        viewModelScope.launch {
            repo.updateAuthor(author)
            get_list()
        }
    }

    fun updateBooks(book: LibraryData) {
        viewModelScope.launch {
            repo.updateBook(book)
            get_list()
        }
    }

    fun deleteAuthor(author: AuthorData) {
        viewModelScope.launch {
            repo.deleteAuthor(author)
            get_list()
        }
    }

    fun deleteBook(book: LibraryData) {
        viewModelScope.launch {
            repo.deleteBook(book)
            get_list()
        }
    }

}