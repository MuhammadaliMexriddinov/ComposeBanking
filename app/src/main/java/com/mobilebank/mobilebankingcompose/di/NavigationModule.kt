package com.mobilebank.mobilebankingcompose.di

import com.mobilebank.mobilebankingcompose.navigation.AppNavigator
import com.mobilebank.mobilebankingcompose.navigation.MyNavigationManager
import com.mobilebank.mobilebankingcompose.navigation.NavigationHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    fun bindNavigationHandler(impl: MyNavigationManager): NavigationHandler

    @Binds
    fun bindAppNavigator(impl: MyNavigationManager): AppNavigator
}