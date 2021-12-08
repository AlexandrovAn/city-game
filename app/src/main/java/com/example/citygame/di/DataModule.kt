package com.example.citygame.di

import android.content.Context
import com.example.citygame.data.db.GameDatabase
import com.example.citygame.data.websocket.SocketHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideGameDb(@ApplicationContext context: Context) = GameDatabase.buildDB(context)

    @Provides
    @Singleton
    fun provideStepDao(db: GameDatabase) = db.stepDao()

}