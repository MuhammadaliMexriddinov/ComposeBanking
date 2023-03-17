package com.mobilebank.mobilebankingcompose.screen.splash

import com.mobilebank.mobilebankingcompose.utils.BaseViewModel


interface SplashViewModel : BaseViewModel<Unit, SplashUiState, Nothing>

data class SplashUiState(
    val isOpenLogin: Boolean = false,
    val isOPenMain: Boolean = false
)