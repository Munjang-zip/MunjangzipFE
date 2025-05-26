//토큰 재발급 응답 모델
package com.example.munjangzip.model

data class RefreshResponse(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val result: RefreshResult
)

data class RefreshResult(
    val accessToken: String,
    val refreshToken: String
)
