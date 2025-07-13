package com.oguzhan.timerlibsample

import android.content.Context

actual fun createTimer(): Timer {
    val context = getAndroidContext() as Context
    return AndroidTimer(context = context)
}