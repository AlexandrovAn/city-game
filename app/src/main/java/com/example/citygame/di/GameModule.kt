package com.example.citygame.di

import com.example.citygame.domain.repositories.CitiesRepository
import com.example.citygame.domain.repositories.SavedWordsRepository
import com.example.citygame.domain.repositories.SocketRepository
import com.example.citygame.domain.usecases.GetGameDetailsUseCase
import com.example.citygame.domain.usecases.SendValuesUseCase
import com.example.citygame.domain.usecases.ValidationUseCase
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
    fun provideSendValuesUseCase(
        socketRepo: SocketRepository,
        savedWordsRepo: SavedWordsRepository
    ) = SendValuesUseCase(socketRepo, savedWordsRepo)

    @Provides
    @Singleton
    fun provideValidationUseCase(
        citiesRepo: CitiesRepository,
        savedWordsRepo: SavedWordsRepository
    ) = ValidationUseCase(citiesRepo, savedWordsRepo)


}