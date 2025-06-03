//토큰 저장 및 불러오기
package com.example.munjangzip.auth

import android.content.Context

object TokenStorage {
    fun saveTokens(context: Context, access: String, refresh: String) {
        val prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
        prefs.edit()
            .putString("accessToken", access)
            .putString("refreshToken", refresh)
            .apply()
    }

    fun getRefreshToken(context: Context): String? {
        return context.getSharedPreferences("auth", Context.MODE_PRIVATE)
            .getString("refreshToken", null)
    }

    fun getAccessToken(context: Context): String? {
        return context.getSharedPreferences("auth", Context.MODE_PRIVATE)
            .getString("accessToken", null)
    }
}
