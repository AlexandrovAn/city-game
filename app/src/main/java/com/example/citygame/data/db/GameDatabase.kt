package com.example.citygame.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.citygame.data.entities.StepEntity

@Database(
    entities = [StepEntity::class],
    version = 1
)
abstract class GameDatabase : RoomDatabase() {

    abstract fun stepDao(): StepDao

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