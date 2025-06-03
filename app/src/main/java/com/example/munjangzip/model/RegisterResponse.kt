package com.example.munjangzip.model

data class RegisterResponse(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val result: RegisterResult
)

data class RegisterResult(
    val memberId: Long,
    val nickname: String,
    val libraryName: String,
    val character: String,
    val characterName: String
)
