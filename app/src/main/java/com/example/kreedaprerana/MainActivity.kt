package com.example.kreedaprerana

import android.os.Bundle
import android.os.SystemClock
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import java.util.Locale

data class Athlete(
    val name: String,
    val age: Int,
    val sport: String,
    val timings: MutableList<Float> = mutableListOf()
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                KreedaPreranaUI()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KreedaPreranaUI() {

    val athletes = remember { mutableStateListOf<Athlete>() }

    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var sport by remember { mutableStateOf("") }
    var batchNames by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Kreeda-Prerana Scout")
                }
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            item {

                Card {

                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {

                        Text(
                            text = "Add Athlete",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Name") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = age,
                            onValueChange = { age = it },
                            label = { Text("Age") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = sport,
                            onValueChange = { sport = it },
                            label = { Text("Sport") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = {

                                if (
                                    name.isNotEmpty() &&
                                    age.isNotEmpty() &&
                                    sport.isNotEmpty()
                                ) {

                                    athletes.add(
                                        Athlete(
                                            name = name,
                                            age = age.toInt(),
                                            sport = sport
                                        )
                                    )

                                    name = ""
                                    age = ""
                                    sport = ""
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Text("Add Athlete")
                        }
                    }
                }
            }

            item {

                Card {

                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {

                        Text(
                            text = "Batch Entry",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = batchNames,
                            onValueChange = { batchNames = it },
                            label = {
                                Text("Enter names separated by comma")
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = {

                                val namesList = batchNames.split(",")

                                for (item in namesList) {

                                    val trimmed = item.trim()

                                    if (trimmed.isNotEmpty()) {

                                        athletes.add(
                                            Athlete(
                                                name = trimmed,
                                                age = 15,
                                                sport = "General"
                                            )
                                        )
                                    }
                                }

                                batchNames = ""
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Text("Add Batch")
                        }
                    }
                }
            }

            item {

                Text(
                    text = "Leaderboard",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            val leaderboard = athletes.sortedBy {

                if (it.timings.isEmpty()) {
                    Float.MAX_VALUE
                } else {
                    it.timings.min()
                }
            }

            itemsIndexed(leaderboard) { _, athlete ->

                AthleteCardUI(athlete)
            }
        }
    }
}

@Composable
fun AthleteCardUI(athlete: Athlete) {

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

            val bestTime =
                if (athlete.timings.isEmpty()) {
                    null
                } else {
                    athlete.timings.min()
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
                    onClick = {
                        running = true
                    }
                ) {
                    Text("Start")
                }

                Button(
                    onClick = {

                        running = false

                        athlete.timings.add(seconds)
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