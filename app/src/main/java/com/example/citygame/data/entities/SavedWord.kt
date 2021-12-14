package com.example.citygame.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_words_table")
data class SavedWord(
    @PrimaryKey
    val word: String
)
