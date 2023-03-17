package com.mobilebank.mobilebankingcompose.di

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.hilt.ScreenModelKey
import com.mobilebank.mobilebankingcompose.screen.login.viewmodel.LoginViewModelImpl
import com.mobilebank.mobilebankingcompose.screen.register.viewmodel.impl.RegisterViewModelImpl
import com.mobilebank.mobilebankingcompose.screen.verify.viewmodel.impl.VerifyViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
abstract class HiltModule {
    @[Binds IntoMap ScreenModelKey(LoginViewModelImpl::class)]
    abstract fun bindLoginScreen(impl: LoginViewModelImpl): ScreenModel

    @[Binds IntoMap ScreenModelKey(VerifyViewModelImpl::class)]
    abstract fun bindVerifyScreen(impl: VerifyViewModelImpl): ScreenModel

    @[Binds IntoMap ScreenModelKey(RegisterViewModelImpl::class)]
    abstract fun registerScreen(impl: RegisterViewModelImpl): ScreenModel
}