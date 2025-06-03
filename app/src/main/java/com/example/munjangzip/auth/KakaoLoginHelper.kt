// 카카오 로그인 처리 + 서버 전송  = 아직 구현 x
package com.example.munjangzip.feature.auth

import android.content.Context
import android.util.Log
import com.kakao.sdk.user.UserApiClient


fun performKakaoLogin(context: Context) {
    UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
        if (error != null) {
            Log.e("KAKAO", "로그인 실패: ${error.localizedMessage}")
        } else if (token != null) {
            val accessToken = token.accessToken
            Log.d("KAKAO", "로그인 성공. accessToken: $accessToken")
            // TODO: 서버로 accessToken POST
        }
    }
}
