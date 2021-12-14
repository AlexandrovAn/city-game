package com.example.citygame.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.citygame.data.entities.City

@Dao
interface CitiesDao {

    @Query("SELECT EXISTS (SELECT * FROM city_table WHERE city = :city)")
    suspend fun exist(city: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(issues: List<City>)

    @Query("DELETE FROM city_table")
    suspend fun clear()

}