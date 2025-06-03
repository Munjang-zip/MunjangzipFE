// Retrofit 인스턴스 싱글톤 구성
package com.example.munjangzip.network

import com.example.munjangzip.auth.TokenAuthenticator
import com.example.munjangzip.auth.TokenManager
import com.example.munjangzip.util.AppContextProvider
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://munjangzip.shop/"

    private val authInterceptor = Interceptor { chain ->
        val accessToken = TokenManager.getAccessToken(AppContextProvider.context)
        val requestBuilder = chain.request().newBuilder()
        if (!accessToken.isNullOrEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer $accessToken")
        }
        chain.proceed(requestBuilder.build())
    }

    private val client = OkHttpClient.Builder()
        .authenticator(TokenAuthenticator())
        .addInterceptor(authInterceptor)
        .build()

    val api: AuthApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApiService::class.java)
    }
}
