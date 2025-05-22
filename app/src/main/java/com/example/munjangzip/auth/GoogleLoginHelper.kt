package com.example.munjangzip.feature.auth

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResult
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException

fun launchGoogleLogin(
    context: Context,
    launcher: ActivityResultLauncher<android.content.Intent>
) {
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("클라이언트 아이디")
        //클라이언트 id
        .requestEmail()
        .build()

    val googleSignInClient = GoogleSignIn.getClient(context, gso)
    launcher.launch(googleSignInClient.signInIntent)
}

fun handleGoogleLoginResult(result: ActivityResult) {
    val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
    try {
        val account = task.getResult(ApiException::class.java)
        val idToken = account.idToken
        Log.d("GOOGLE", "로그인 성공. ID Token: $idToken")
        // TODO: 서버로 idToken POST
    } catch (e: ApiException) {
        Log.e("GOOGLE", "로그인 실패: ${e.statusCode}")
    }
}
