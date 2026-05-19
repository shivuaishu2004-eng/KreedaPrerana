package com.example.kreedaprerana.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kreedaprerana.data.Athlete
import com.example.kreedaprerana.ui.components.AddAthleteCard
import com.example.kreedaprerana.ui.components.AthleteCardUI
import com.example.kreedaprerana.ui.components.BatchEntryCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KreedaPreranaUI() {
    val athletes = remember { mutableStateListOf<Athlete>() }

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
                AddAthleteCard(
                    onAthleteAdded = { newAthlete ->
                        athletes.add(newAthlete)
                    }
                )
            }

            item {
                BatchEntryCard(
                    onBatchAdded = { batch ->
                        athletes.addAll(batch)
                    }
                )
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
                    it.timings.minOrNull() ?: Float.MAX_VALUE
                }
            }

            itemsIndexed(leaderboard) { _, athlete ->
                AthleteCardUI(
                    athlete = athlete,
                    onTimeRecorded = { seconds ->
                        athlete.timings.add(seconds)
                        val index = athletes.indexOf(athlete)
                        if (index != -1) {
                            athletes[index] = athlete.copy(timings = athlete.timings.toMutableList())
                        }
                    }
                )
            }
        }
    }
}
