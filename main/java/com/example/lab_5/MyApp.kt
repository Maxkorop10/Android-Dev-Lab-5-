package com.example.lab_5

import android.app.Application
import com.example.lab_5.Database.SuperDB
import com.example.lab_5.Repo.MyRepo

class MyApp: Application() {
    private val super_database by lazy { SuperDB.getDatabase(this) }
    val repo by lazy { MyRepo(super_database.authorDao(), super_database.bookDao()) }
}