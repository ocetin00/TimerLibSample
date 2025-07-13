package com.oguzhan.timerlibsample

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class TimerService : Service() {

    private var timerJob: Job? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val durationMillis = intent?.getLongExtra("duration", 0L) ?: 0L
        val intervalMillis = intent?.getLongExtra("interval", 1000L) ?: 1000L

        timerJob?.cancel()
        timerJob = CoroutineScope(Dispatchers.Default).launch {
            var remaining = durationMillis

            while (remaining > 0) {
                delay(intervalMillis)
                remaining -= intervalMillis

                sendUpdateBroadcast(remaining)
            }

            sendUpdateBroadcast(0L)
            stopSelf()
        }

        return START_STICKY
    }

    private fun sendUpdateBroadcast(remaining: Long) {
        val intent = Intent("com.oguzhan.timer.UPDATE")
        intent.putExtra("remaining", remaining)
        sendBroadcast(intent)
    }


    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        timerJob?.cancel()
    }
}