package com.example.eom_fe.data

data class ChallengeData(
    val id: Int,
    val title: String,
    val deadline: String,
    val challengeStatusCode: String,
    val validationIntervalCode: String,
    val validationCountPerInterval: Int,
    val categoryCode: String,
    val validatorList: List<String>
) : java.io.Serializable
