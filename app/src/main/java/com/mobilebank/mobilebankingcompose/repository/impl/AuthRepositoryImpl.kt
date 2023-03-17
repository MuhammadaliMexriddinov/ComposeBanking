package com.mobilebank.mobilebankingcompose.repository.impl

import com.mobilebank.mobilebankingcompose.data.local.MySharedPref
import com.mobilebank.mobilebankingcompose.data.remote.api.AuthApi
import com.mobilebank.mobilebankingcompose.data.remote.request.login.LoginRequest
import com.mobilebank.mobilebankingcompose.data.remote.request.register.SignUpRequest
import com.mobilebank.mobilebankingcompose.data.remote.request.resend_verify.ResendVerifyRequestAndResponse
import com.mobilebank.mobilebankingcompose.data.remote.request.verify.SignInVerifyRequest
import com.mobilebank.mobilebankingcompose.data.remote.request.verify.SignUpVerifyRequest
import com.mobilebank.mobilebankingcompose.data.remote.response.login.LoginResponse
import com.mobilebank.mobilebankingcompose.data.remote.response.register.SignUpSuccessResponse
import com.mobilebank.mobilebankingcompose.repository.AuthRepository
import com.mobilebank.mobilebankingcompose.utils.ResultData
import com.mobilebank.mobilebankingcompose.utils.func
import kotlinx.coroutines.flow.flow
import com.mobilebank.mobilebankingcompose.data.remote.response.verify.VerifyResponse
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(var sharedPref: MySharedPref, var authApi: AuthApi) :
    AuthRepository {
    override fun login(request: LoginRequest) = flow<ResultData<LoginResponse>> {
        val authResponse = authApi.signIn(request).func()
        emit(authResponse)
        authResponse.onSuccess {
            sharedPref.verifyToken = it.token
        }
    }


    override fun signInVerify(code: String) = flow<ResultData<VerifyResponse>> {
        val authResponse =
            authApi.signInVerify(SignInVerifyRequest(code = code, sharedPref.verifyToken)).func()
        emit(authResponse)
        authResponse.onSuccess {
            sharedPref.refreshToken = it.refreshToken
            sharedPref.token = it.accessToken
        }
    }

    override fun signUpVerify(code: String) = flow<ResultData<VerifyResponse>> {
        val authResponse =
            authApi.signUpVerify(SignUpVerifyRequest(code = code, sharedPref.verifyToken)).func()
        emit(authResponse)
        authResponse.onSuccess {
            sharedPref.refreshToken = it.refreshToken
            sharedPref.token = it.accessToken
        }
    }

    override fun signUpResend() = flow<ResultData<ResendVerifyRequestAndResponse>> {
        val authResponse =
            authApi.signUpVerifyResend(ResendVerifyRequestAndResponse(sharedPref.verifyToken))
                .func()
        emit(authResponse)
        authResponse.onSuccess {
            sharedPref.verifyToken = it.token

        }
    }

    override fun signInResend() = flow<ResultData<ResendVerifyRequestAndResponse>> {

        val authResponse =
            authApi.signInVerifyResend(ResendVerifyRequestAndResponse(sharedPref.verifyToken))
                .func()
        emit(authResponse)
        authResponse.onSuccess {
            sharedPref.verifyToken = it.token

        }
    }

    override fun signUp(verifyRequest: SignUpRequest) = flow<ResultData<SignUpSuccessResponse>> {
        val authResponse = authApi.signUp(verifyRequest).func()
        emit(authResponse)
        authResponse.onSuccess {
            sharedPref.verifyToken = it.token

        }
    }

}