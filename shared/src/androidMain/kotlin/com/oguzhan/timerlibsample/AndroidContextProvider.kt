package com.oguzhan.timerlibsample

import android.content.Context

// androidMain
private var appContext: Context? = null

fun setAndroidContext(context: Context) {
    appContext = context.applicationContext
}

actual fun getAndroidContext(): Any {
    return appContext ?: throw IllegalStateException("Context not set")
}