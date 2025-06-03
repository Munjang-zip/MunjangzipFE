package com.example.munjangzip.util

import android.app.Application
import android.content.Context

@Suppress("StaticFieldLeak")
class AppContextProvider : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        @Suppress("StaticFieldLeak")
        lateinit var context: Context
            private set
    }
}
