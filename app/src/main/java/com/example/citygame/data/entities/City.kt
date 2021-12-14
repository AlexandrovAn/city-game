package com.example.citygame.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_table")
data class City(
    val region: String,
    @PrimaryKey
    val city: String
)