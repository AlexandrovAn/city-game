package com.example.citygame.di

import com.example.citygame.data.websocket.SocketRepoImpl
import com.example.citygame.domain.repositories.SocketRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun bindSocketRepo(impl : SocketRepoImpl): SocketRepository

}