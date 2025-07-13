package com.oguzhan.timerlibsample

import kotlinx.coroutines.flow.Flow


interface Timer {
    fun start(seconds: Long)
    fun stop()
    suspend fun pause()
    fun resume()
    fun collectTimer(): Flow<Long>
}

expect fun createTimer(): Timer
