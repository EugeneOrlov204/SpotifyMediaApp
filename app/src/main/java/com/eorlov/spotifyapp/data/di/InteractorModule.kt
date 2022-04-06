package com.eorlov.spotifyapp.data.di

import com.eorlov.spotifyapp.data.interactor.UserInteractorImpl
import com.eorlov.spotifyapp.domain.interactor.UserInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class InteractorModule {

    @Binds
    abstract fun bindUserInteractor(userInteractor: UserInteractorImpl): UserInteractor
}