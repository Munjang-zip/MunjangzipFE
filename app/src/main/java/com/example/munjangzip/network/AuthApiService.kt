// 로그인 & 토큰 갱신 API 인터페이스
package com.example.munjangzip.network

import com.example.munjangzip.model.JwtResponse
import com.example.munjangzip.model.RefreshResponse
import com.example.munjangzip.model.RegisterRequest
import com.example.munjangzip.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApiService {

    @POST("/member/oauth/login")
    fun loginWithGoogle(
        @Query("provider") provider: String = "GOOGLE",
        @Query("token") token: String
    ): Call<JwtResponse>


    @POST("/member/token/refresh")
    fun refreshAccessToken(
        @Query("refreshToken") token: String
    ): Call<RefreshResponse>

    @POST("/member/register")
    fun registerUser(
        @Body request: RegisterRequest
    ): Call<RegisterResponse>






}