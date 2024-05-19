package com.example.loginmvvm.core.di

import com.example.loginmvvm.core.log.Logger
import com.example.loginmvvm.core.log.LoggerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoggerModule {

    @Provides
    @Singleton
    fun providesLogger(): Logger = LoggerImpl()
}