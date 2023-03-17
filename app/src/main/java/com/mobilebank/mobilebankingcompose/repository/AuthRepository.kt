package com.mobilebank.mobilebankingcompose.repository

import com.mobilebank.mobilebankingcompose.data.remote.request.login.LoginRequest
import com.mobilebank.mobilebankingcompose.data.remote.request.register.SignUpRequest
import com.mobilebank.mobilebankingcompose.data.remote.request.resend_verify.ResendVerifyRequestAndResponse
import com.mobilebank.mobilebankingcompose.data.remote.response.login.LoginResponse
import com.mobilebank.mobilebankingcompose.data.remote.response.register.SignUpSuccessResponse
import com.mobilebank.mobilebankingcompose.utils.ResultData
import kotlinx.coroutines.flow.Flow
import com.mobilebank.mobilebankingcompose.data.remote.response.verify.VerifyResponse

interface AuthRepository {
    fun login(request: LoginRequest):Flow<ResultData<LoginResponse>>
    fun signInVerify(token:String):Flow<ResultData<VerifyResponse>>
    fun signUpVerify(token:String):Flow<ResultData<VerifyResponse>>
    fun signUp(verifyRequest: SignUpRequest):Flow<ResultData<SignUpSuccessResponse>>
    fun signUpResend(): Flow<ResultData<ResendVerifyRequestAndResponse>>
    fun signInResend(): Flow<ResultData<ResendVerifyRequestAndResponse>>
}