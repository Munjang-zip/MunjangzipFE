//구글 로그인 처리 + 구글 로그인 서버 POST
package com.example.munjangzip.feature.auth

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import com.example.munjangzip.R
import com.example.munjangzip.model.JwtResponse
import com.example.munjangzip.network.RetrofitClient
import com.example.munjangzip.auth.TokenStorage
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun launchGoogleLogin(context: Context, launcher: ActivityResultLauncher<Intent>) {
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.server_client_id))
        .requestEmail()
        .build()

    val googleSignInClient = GoogleSignIn.getClient(context, gso)
    launcher.launch(googleSignInClient.signInIntent)
}
fun handleGoogleLoginResult(context: Context, result: ActivityResult) {
    val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
    try {
        val account = task.getResult(ApiException::class.java)
        val idToken = account.idToken ?: run {
            Log.e("GOOGLE", "ID Token이 null입니다. 계정: ${account.email}")
            return
        }

        Log.d("GOOGLE", """
            ✅ 구글 로그인 성공
            ▶ Email: ${account.email}
            ▶ Display Name: ${account.displayName}
            ▶ ID Token: $idToken
        """.trimIndent())

        //서버에 id 토큰 전송
        RetrofitClient.api.loginWithGoogle(token = idToken)
            .enqueue(object : Callback<JwtResponse> {
                override fun onResponse(call: Call<JwtResponse>, response: Response<JwtResponse>) {
                    Log.d("✅GOOGLE", "응답 전체: ${response.body()}")
                    Log.d("✅GOOGLE", "상태 코드: ${response.code()}")

                    if (response.isSuccessful) {
                        val result = response.body()?.result
                        if (result != null) {
                            Log.d("✅GOOGLE", "로그인 응답에서 받은 refreshToken: ${result.refreshToken}")
                            TokenStorage.saveTokens(context, result.accessToken, result.refreshToken)
                        }

                } else {
                        Log.w("GOOGLE", "서버 응답 실패: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<JwtResponse>, t: Throwable) {
                    Log.e("GOOGLE", "서버 요청 실패", t)
                }
            })

    } catch (e: ApiException) {
        Log.e("GOOGLE", "로그인 실패: ${e.statusCode}", e)
    }
}
