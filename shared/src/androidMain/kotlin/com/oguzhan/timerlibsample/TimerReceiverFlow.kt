package com.oguzhan.timerlibsample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun timerTickFlow(context: Context): Flow<Long> = callbackFlow {
    val receiver = object : BroadcastReceiver() {
        override fun onReceive(ctx: Context?, intent: Intent?) {
            val remaining = intent?.getLongExtra("remaining", 0L) ?: return
            trySend(remaining)
        }
    }

    val filter = IntentFilter("com.oguzhan.timer.UPDATE")
    context.registerReceiver(receiver, filter)

    awaitClose {
        context.unregisterReceiver(receiver)
    }
}
