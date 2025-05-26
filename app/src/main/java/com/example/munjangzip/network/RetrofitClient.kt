// Retrofit 인스턴스 싱글톤 구성
package com.example.munjangzip.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = ""//  서버 주소

    val api: AuthApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApiService::class.java)
    }
}