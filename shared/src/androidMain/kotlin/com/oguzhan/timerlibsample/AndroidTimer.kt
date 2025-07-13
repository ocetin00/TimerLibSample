package com.oguzhan.timerlibsample

import android.content.Context
import android.content.Intent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class AndroidTimer(private val context: Context) : Timer {

    private var totalDuration = 0L
    private var remainingTime = 0L
    private var interval = 1000L // Default interval of 1 second


    override fun start(durationMillis: Long) {
        remainingTime = 0L
        val intent = Intent(context, TimerService::class.java).apply {
            putExtra("duration", durationMillis)
            putExtra("interval", interval) // Default interval of 1 second
        }
        context.startService(intent)
    }

    override fun stop() {
        context.stopService(Intent(context, TimerService::class.java))
    }

    override suspend fun pause() {
        remainingTime = timerTickFlow(context).first()
        stop()
    }

    override fun resume() {
        if (remainingTime > 0) {
            start(remainingTime)
        } else {
            start(totalDuration)
        }
    }

    override fun collectTimer(): Flow<Long> {
        return timerTickFlow(context)
    }
}