package com.mobilebank.mobilebankingcompose.data.local

import android.content.Context
import android.content.SharedPreferences
import com.mobilebank.mobilebankingcompose.utils.SharedPreference
import javax.inject.Inject

class MySharedPref @Inject constructor(context: Context, sharedPref: SharedPreferences) :
    SharedPreference(context, sharedPref) {
    var token: String by Strings("")
    var refreshToken: String by Strings("")
    var verifyToken:String by Strings("")
    var phone: String by Strings()
    var name: String by Strings("Username")


}