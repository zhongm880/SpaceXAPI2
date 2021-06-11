package com.example.capgemini_spacex.model.data

import com.example.spacexapi.model.Fairings
import com.example.spacexapi.model.FirstStage

data class Rocket(
    val fairings: Fairings,
    val first_stage: FirstStage,
    val rocket_id: String,
    val rocket_name: String,
    val rocket_type: String,
    val second_stage: SecondStage
)