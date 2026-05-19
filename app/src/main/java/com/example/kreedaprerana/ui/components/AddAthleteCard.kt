package com.example.kreedaprerana.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kreedaprerana.data.Athlete

@Composable
fun AddAthleteCard(
    onAthleteAdded: (Athlete) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var sport by remember { mutableStateOf("") }

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
                    if (name.isNotEmpty() && age.isNotEmpty() && sport.isNotEmpty()) {
                        val ageInt = age.toIntOrNull() ?: 15
                        onAthleteAdded(
                            Athlete(
                                name = name,
                                age = ageInt,
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
