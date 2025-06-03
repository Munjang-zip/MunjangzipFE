package com.example.munjangzip.data

import com.example.munjangzip.R

data class Pet(
    val name: String,         // 서버로 보낼 이름
    val imageRes: Int         // 이미지 리소스
)

val petList = listOf(
    Pet("fish", R.drawable.fish),
    Pet("fish_blue", R.drawable.fish_blue),
    Pet("rabbit", R.drawable.rabbit),
    Pet("cat", R.drawable.cat),
)
