package com.example.munjangzip

import android.app.Application
import com.kakao.sdk.common.KakaoSdk


class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "ㅇㅇㅇ") //네이티브 앱 키
    }
}