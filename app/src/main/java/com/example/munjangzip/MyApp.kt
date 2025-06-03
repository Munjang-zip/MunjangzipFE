package com.example.munjangzip

import android.app.Application
import com.kakao.sdk.common.KakaoSdk


class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "ㅇㅇ") //네이티브 앱 키
    }
}