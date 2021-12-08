package com.example.citygame.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "step_table")
data class StepEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val number: Int,
    val word: String
)
