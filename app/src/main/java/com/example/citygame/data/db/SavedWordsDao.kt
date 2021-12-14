package com.example.citygame.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.citygame.data.entities.SavedWord

@Dao
interface SavedWordsDao {

    @Query("SELECT EXISTS (SELECT * FROM saved_words_table WHERE word = :word)")
    suspend fun exist(word: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveWord(savedWord: SavedWord)

    @Query("DELETE FROM saved_words_table")
    suspend fun clear()

}