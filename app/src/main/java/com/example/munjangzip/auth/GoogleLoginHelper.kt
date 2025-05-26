//구글 로그인 처리 + 서버 POST
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

        RetrofitClient.api.loginWithGoogle(token = idToken)
            .enqueue(object : Callback<JwtResponse> {
                override fun onResponse(call: Call<JwtResponse>, response: Response<JwtResponse>) {
                    if (response.isSuccessful) {
                        val result = response.body()?.result
                        if (result != null) {
                            TokenStorage.saveTokens(context, result.accessToken, result.refreshToken)
                            Log.d("GOOGLE", """
                                ✅ 서버 로그인 성공
                                ▶ AccessToken: ${result.accessToken}
                                ▶ RefreshToken: ${result.refreshToken}
                                ▶ MemberId: ${result.memberId}
                            """.trimIndent())
                        } else {
                            Log.w("GOOGLE", "서버 응답 성공했지만 result가 null입니다.")
                        }
                    } else {
                        val errorBody = response.errorBody()?.string()
                        Log.e("GOOGLE", """
                            ❌ 서버 응답 오류
                            ▶ HTTP 코드: ${response.code()}
                            ▶ 메시지: ${response.message()}
                            ▶ 에러 바디: $errorBody
                        """.trimIndent())
                    }
                }

                override fun onFailure(call: Call<JwtResponse>, t: Throwable) {
                    Log.e("GOOGLE", "❌ 서버 요청 실패", t)
                }
            })

    } catch (e: ApiException) {
        Log.e("GOOGLE", """
            ❌ 구글 로그인 실패
            ▶ Status Code: ${e.statusCode}
            ▶ Message: ${e.message}
        """.trimIndent(), e)
    }
}
