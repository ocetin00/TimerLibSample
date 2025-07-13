package com.oguzhan.timerlibsample.android

import android.app.Application
import com.oguzhan.timerlibsample.setAndroidContext

class TimerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setAndroidContext(this)
    }
}