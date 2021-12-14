package com.example.citygame.di

import com.example.citygame.data.repositories.CitiesRepoImpl
import com.example.citygame.data.repositories.SavedWordsRepoImpl
import com.example.citygame.data.repositories.SocketRepoImpl
import com.example.citygame.domain.repositories.CitiesRepository
import com.example.citygame.domain.repositories.SavedWordsRepository
import com.example.citygame.domain.repositories.SocketRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun bindSocketRepo(impl: SocketRepoImpl): SocketRepository

    @Binds
    fun bindCitiesRepo(impl: CitiesRepoImpl): CitiesRepository

    @Binds
    fun bindSavedWordsRepo(impl: SavedWordsRepoImpl): SavedWordsRepository

}