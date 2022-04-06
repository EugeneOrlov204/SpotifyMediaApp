package com.eorlov.spotifyapp.data.di

import com.eorlov.spotifyapp.data.repository.UserRepositoryImpl
import com.eorlov.spotifyapp.data.storage.SharedPreferencesStorage
import com.eorlov.spotifyapp.domain.repository.UserRepository
import com.eorlov.spotifyapp.domain.storage.Storage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(userRepository: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun bindSharedPreferencesStorage(
        sharedPreferencesStorage: SharedPreferencesStorage
    ): Storage
}