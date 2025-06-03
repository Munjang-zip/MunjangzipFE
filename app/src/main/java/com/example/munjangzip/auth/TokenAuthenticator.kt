package com.example.munjangzip.auth

import android.util.Log
import com.example.munjangzip.util.AppContextProvider
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        Log.d("AUTH", "401 발생 - 토큰 갱신 시도")
        val context = AppContextProvider.context
        val newToken = TokenRefreshHelper.refreshTokenBlocking(context) ?: return null

        return response.request.newBuilder()
            .header("Authorization", "Bearer $newToken")
            .build()
    }
}
