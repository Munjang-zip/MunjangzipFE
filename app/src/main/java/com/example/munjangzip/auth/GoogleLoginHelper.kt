//구글 로그인 처리 + 구글 로그인 서버 POST
package com.example.munjangzip.feature.auth
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import com.example.munjangzip.R
import com.example.munjangzip.auth.TokenManager
import com.example.munjangzip.model.JwtResponse
import com.example.munjangzip.network.RetrofitClient

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

fun handleGoogleLoginResult(context: Context, result: ActivityResult, onSuccess: () -> Unit) {
    val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
    try {
        val account = task.getResult(ApiException::class.java)
        val idToken = account.idToken ?: return

        Log.d("GOOGLE", "✅ 구글 로그인 성공\n▶ Email: ${account.email}\n▶ Display Name: ${account.displayName}\n▶ ID Token: $idToken")

        RetrofitClient.api.loginWithGoogle(token = idToken)
            .enqueue(object : Callback<JwtResponse> {
                override fun onResponse(call: Call<JwtResponse>, response: Response<JwtResponse>) {
                    Log.d("✅GOOGLE", "응답 전체: ${response.body()}")
                    if (response.isSuccessful && response.body()?.result != null) {
                        val result = response.body()!!.result
                        Log.d("✅TOKEN", "accessToken 저장됨: ${result.accessToken}")
                        TokenManager.saveTokens(context, result.accessToken, result.refreshToken)
                        // ✅ 성공 시 다음 화면으로 이동
                        onSuccess()
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
