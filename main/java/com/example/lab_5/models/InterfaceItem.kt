package com.example.lab_5.models

interface InterfaceItem {
    fun getItemType(): Int

    companion object {
        const val book = 1
        const val author = 2
    }
}