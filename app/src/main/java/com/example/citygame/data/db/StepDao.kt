package com.example.citygame.data.db

import androidx.room.*
import com.example.citygame.data.entities.StepEntity

@Dao
interface StepDao {

    @Query("SELECT * FROM step_table")
    suspend fun getAll() : List<StepEntity>

    @Delete
    suspend fun deleteStep(step: StepEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStep(step : StepEntity)
}