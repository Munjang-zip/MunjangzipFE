//로그인 응답 모델
package com.example.munjangzip.model

data class JwtResponse(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val result: TokenResult
)

data class TokenResult(
    val accessToken: String,
    val refreshToken: String,
    val memberId: Long
)
