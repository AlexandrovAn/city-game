package com.example.citygame.di

import com.example.citygame.domain.repositories.SocketRepository
import com.example.citygame.domain.usecases.GetGameDetailsUseCase
import com.example.citygame.domain.usecases.SendValuesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class GameModule {

    @Provides
    @Singleton
    fun provideGameUseCase(repo: SocketRepository) = GetGameDetailsUseCase(repo)

    @Provides
    @Singleton
    fun provideSendValuesUseCase(repo: SocketRepository) = SendValuesUseCase(repo)


}