//토큰 재발급 처리
package com.example.munjangzip.auth

import android.content.Context
import android.util.Log
import com.example.munjangzip.model.RefreshResponse
import com.example.munjangzip.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun refreshTokenIfNeeded(context: Context) {
    val refresh = TokenStorage.getRefreshToken(context) ?: return

    RetrofitClient.api.refreshAccessToken(refresh)
        .enqueue(object : Callback<RefreshResponse> {
            override fun onResponse(call: Call<RefreshResponse>, response: Response<RefreshResponse>) {
                if (response.isSuccessful) {
                    val newTokens = response.body()?.result
                    if (newTokens != null) {
                        TokenStorage.saveTokens(context, newTokens.accessToken, newTokens.refreshToken)
                        Log.d("AUTH", "토큰 갱신 완료")
                    }
                }
            }

            override fun onFailure(call: Call<RefreshResponse>, t: Throwable) {
                Log.e("AUTH", "토큰 갱신 실패", t)
            }
        })
}
