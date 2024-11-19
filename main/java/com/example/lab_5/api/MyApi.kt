package com.example.lab_5.api

import com.example.lab_5.models.AuthorData
import com.example.lab_5.models.LibraryData
import retrofit2.Response
import retrofit2.http.GET

interface MyApi {
    @GET("authors")
    suspend fun getAuthors(): Response<List<AuthorData>>

    @GET("books")
    suspend fun getBooks(): Response<List<LibraryData>>
}