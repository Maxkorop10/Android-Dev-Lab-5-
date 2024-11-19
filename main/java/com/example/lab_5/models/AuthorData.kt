package com.example.lab_5.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


@Entity(tableName = "authors")

data class AuthorData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @field:Json(name = "full_name") val full_name: String,
    @field:Json(name = "homecountry") val homecountry: String,
) : InterfaceItem {
    override fun getItemType(): Int {
        return InterfaceItem.author
    }
}
