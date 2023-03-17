package com.mobilebank.mobilebankingcompose.screen.verify.viewmodel.impl

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import cafe.adriel.voyager.core.screen.Screen
import com.mobilebank.mobilebankingcompose.navigation.AppNavigator
import com.mobilebank.mobilebankingcompose.repository.AuthRepository
import com.mobilebank.mobilebankingcompose.screen.main.MainScreen
import com.mobilebank.mobilebankingcompose.screen.verify.viewmodel.VerifyContract
import com.mobilebank.mobilebankingcompose.utils.SignType
import com.mobilebank.mobilebankingcompose.utils.VerifyUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class VerifyViewModelImpl @Inject constructor(
    private val repository: AuthRepository,
    private val appNavigator: AppNavigator,
) : VerifyContract.VerifyModel, ScreenModel {


    override val uiState = MutableStateFlow<VerifyContract.UiState>(
        VerifyContract.UiState(
            code = "",
            errorMessage = "", progress = false,
            resendTime = 60
        )
    )

    override fun onEventDispatcher(intent: VerifyContract.VerifyIntent) {
        when (intent) {
            VerifyContract.VerifyIntent.ClickVerify -> {
                when (VerifyUtil.signType) {
                    SignType.SIGN_UP -> {
                        repository.signUpVerify(uiState.value.code).onEach { result ->
                            reduce {
                                it.copy(progress = false)
                            }
                            result.onSuccess {
                                open(MainScreen())
                            }
                            result.onError { t ->
                                reduce {
                                    it.copy(progress = true, errorMessage = t.message.toString())
                                }

                            }
                        }.launchIn(coroutineScope)
                    }
                    SignType.SIGN_IN -> {
                        repository.signInVerify(uiState.value.code).onEach { result ->
                            reduce {
                                it.copy(progress = false)
                            }
                            result.onSuccess {
                                open(MainScreen())
                            }
                            result.onError { t ->
                                reduce {
                                    it.copy(progress = true, errorMessage = t.message.toString())
                                }

                            }
                        }.launchIn(coroutineScope)
                    }
                }
            }
            is VerifyContract.VerifyIntent.CodeEnter -> {
                reduce {
                    it.copy(code = intent.code, progress = false)
                }
            }
            VerifyContract.VerifyIntent.ClickResend -> {
                when (VerifyUtil.signType) {
                    SignType.SIGN_UP -> {
                        repository.signUpResend().onEach {
                            it.onSuccess {
                                reduce { reduse->
                                    reduse.copy(progress = true, errorMessage = "Muvaffaqiyatli kod Qayta Yuborildi")
                                }
                            }
                            it.onError {
                                reduce { reduse->
                                    reduse.copy(progress = true, errorMessage = it.message.toString())
                                }
                            }
                        }.launchIn(coroutineScope)
                    }
                    SignType.SIGN_IN -> {
                        repository.signInResend().onEach {
                            it.onSuccess {
                                reduce { reduse->
                                    reduse.copy(progress = true, errorMessage = "Muvaffaqiyatli kod Qayta Yuborildi")
                                }
                            }
                            it.onError {
                                reduce { reduse->
                                    reduse.copy(progress = true, errorMessage = it.message.toString())
                                }
                            }
                        }.launchIn(coroutineScope)

                    }
                }
            }
        }

    }


    private fun open(screen: Screen) {
        coroutineScope.launch {

            appNavigator.navigationTo(screen)
        }
    }


    private fun reduce(block: (oldState: VerifyContract.UiState) -> VerifyContract.UiState) {
        val old = uiState.value
        uiState.value = block(old)
    }

}