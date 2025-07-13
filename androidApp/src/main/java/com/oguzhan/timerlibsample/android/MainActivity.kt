package com.oguzhan.timerlibsample.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.oguzhan.timerlibsample.createTimer
import com.oguzhan.timerlibsample.setAndroidContext
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setAndroidContext(this)
        val timer = createTimer()
        timer.start(10000L)


        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val leftTimer = timer.collectTimer().collectAsState(0L)

                    val scope = rememberCoroutineScope()
                    Column {
                        Text("Left Timer: ${leftTimer.value / 1000} seconds")
                        Button(onClick = {
                            timer.start(10000L)
                        }) {
                            Text("start")
                        }

                        Button(onClick = {
                            timer.stop()
                        }) {
                            Text("stop")
                        }

                        Button(onClick = {
                            scope.launch {
                                timer.pause()
                            }
                        }) {
                            Text("pause")
                        }

                        Button(onClick = {
                            timer.resume()
                        }) {
                            Text("resume")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
