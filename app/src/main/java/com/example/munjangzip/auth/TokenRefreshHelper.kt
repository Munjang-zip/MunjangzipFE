//토큰 재발급 처리
package com.example.munjangzip.auth

import android.content.Context
import com.example.munjangzip.model.RefreshResponse
import com.example.munjangzip.network.RetrofitClient
import retrofit2.Call
import retrofit2.Response

object TokenRefreshHelper {
    fun refreshTokenBlocking(context: Context): String? {
        val refreshToken = TokenManager.getRefreshToken(context) ?: return null
        val call: Call<RefreshResponse> = RetrofitClient.api.refreshAccessToken(refreshToken)

        return try {
            val response: Response<RefreshResponse> = call.execute()
            if (response.isSuccessful && response.body()?.isSuccess == true) {
                val result = response.body()!!.result
                TokenManager.saveTokens(context, result.accessToken, result.refreshToken)
                result.accessToken
            } else null
        } catch (e: Exception) {
            null
        }
    }
}
