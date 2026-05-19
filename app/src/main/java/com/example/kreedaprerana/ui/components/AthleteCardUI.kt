package com.example.kreedaprerana.ui.components

import android.os.SystemClock
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kreedaprerana.data.Athlete
import kotlinx.coroutines.delay
import java.util.Locale

@Composable
fun AthleteCardUI(
    athlete: Athlete,
    onTimeRecorded: (Float) -> Unit
) {
    var running by remember { mutableStateOf(false) }
    var time by remember { mutableLongStateOf(0L) }

    LaunchedEffect(running) {
        if (running) {
            val startTime = SystemClock.elapsedRealtime() - time
            while (running) {
                time = SystemClock.elapsedRealtime() - startTime
                delay(10)
            }
        }
    }

    val seconds = time / 1000f

    Card {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = athlete.name,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Age: ${athlete.age} | Sport: ${athlete.sport}"
            )

            Spacer(modifier = Modifier.height(8.dp))

            val bestTime = if (athlete.timings.isEmpty()) {
                null
            } else {
                athlete.timings.minOrNull()
            }

            Text(
                text = "Best Time: ${
                    if (bestTime != null) {
                        String.format(Locale.US, "%.2f", bestTime)
                    } else {
                        "--"
                    }
                } sec"
            )

            Text(
                text = "Current Time: ${
                    String.format(Locale.US, "%.2f", seconds)
                } sec"
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { running = true }
                ) {
                    Text("Start")
                }

                Button(
                    onClick = {
                        running = false
                        onTimeRecorded(seconds)
                    }
                ) {
                    Text("Stop")
                }

                Button(
                    onClick = {
                        running = false
                        time = 0
                    }
                ) {
                    Text("Reset")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (athlete.timings.isNotEmpty()) {
                Text("Talent Curve:")
                Text(
                    athlete.timings.joinToString(" → ") {
                        String.format(Locale.US, "%.2f", it)
                    }
                )
            }

            if (bestTime != null && bestTime < 12f) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "🏅 District Level Ready",
                    color = Color.Blue
                )
            }
        }
    }
}
