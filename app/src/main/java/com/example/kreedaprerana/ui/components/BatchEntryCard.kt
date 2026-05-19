package com.example.kreedaprerana.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kreedaprerana.data.Athlete

@Composable
fun BatchEntryCard(
    onBatchAdded: (List<Athlete>) -> Unit
) {
    var batchNames by remember { mutableStateOf("") }

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
                    val newAthletes = mutableListOf<Athlete>()
                    for (item in namesList) {
                        val trimmed = item.trim()
                        if (trimmed.isNotEmpty()) {
                            newAthletes.add(
                                Athlete(
                                    name = trimmed,
                                    age = 15,
                                    sport = "General"
                                )
                            )
                        }
                    }
                    if (newAthletes.isNotEmpty()) {
                        onBatchAdded(newAthletes)
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
