package com.example.lab_5.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(
    tableName = "books",
    foreignKeys = [
        ForeignKey(
            entity = AuthorData::class,
            parentColumns = ["id"],
            childColumns = ["authorID"],
            onDelete = ForeignKey.CASCADE
        )
    ], indices = [Index(value = ["authorID"])]
)

data class LibraryData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @field:Json(name = "authorID") val authorID: Int,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "prod_year") val prod_year: String
) : InterfaceItem {
    override fun getItemType(): Int {
        return InterfaceItem.book
    }
}
