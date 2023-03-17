package com.mobilebank.mobilebankingcompose.di

import com.mobilebank.mobilebankingcompose.repository.AuthRepository
import com.mobilebank.mobilebankingcompose.repository.impl.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun getRepositoryAuth(repository: AuthRepositoryImpl):AuthRepository
}