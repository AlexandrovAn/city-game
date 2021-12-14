package com.example.citygame.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.citygame.data.entities.City
import com.example.citygame.data.entities.SavedWord

@Database(
    entities = [SavedWord::class, City::class],
    version = 2
)
abstract class GameDatabase : RoomDatabase() {

    abstract fun savedWordsDao(): SavedWordsDao
    abstract fun cityDao(): CitiesDao

    companion object {
        private const val NAME = "game.db"

        fun buildDB(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            GameDatabase::class.java,
            NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}