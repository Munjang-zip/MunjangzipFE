package com.example.munjangzip.auth

import android.content.Context
import android.util.Log
import com.example.munjangzip.network.AuthApiService
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TokenAuthenticator(
    private val context: Context
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshToken = TokenManager.getRefreshToken(context)

        if (refreshToken.isNullOrEmpty()) {
            Log.e("AUTH", "refresh token 없음")
            return null
        }

        // 서버에 토큰 갱신 요청
        val tokenResponse = runCatching {
            Retrofit.Builder()
                .baseUrl("https://munjangzip.shop/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AuthApiService::class.java)
                .refreshAccessToken(refreshToken)
                .execute()
        }.getOrNull()

        if (tokenResponse?.isSuccessful == true) {
            val newAccessToken = tokenResponse.body()?.result?.accessToken ?: return null
            val newRefreshToken = tokenResponse.body()?.result?.refreshToken ?: return null

            // 토큰 저장
            TokenManager.saveTokens(context, newAccessToken, newRefreshToken)

            Log.d("AUTH", "토큰 갱신 성공")

            return response.request.newBuilder()
                .header("Authorization", "Bearer $newAccessToken")
                .build()
        }

        Log.e("AUTH", " 토큰 갱신 실패")
        return null
    }
}
