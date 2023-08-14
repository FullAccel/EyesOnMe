package com.example.eom_fe.data

data class ChallengeRequestData(
    val title: String,
    val deadline: String,
    val validationIntervalCode: String,
    val validationCountPerInterval: Int,
    val categoryCode: String
) : java.io.Serializable
