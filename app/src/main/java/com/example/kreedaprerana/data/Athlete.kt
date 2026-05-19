package com.example.kreedaprerana.data

data class Athlete(
    val name: String,
    val age: Int,
    val sport: String,
    val timings: MutableList<Float> = mutableListOf()
)
