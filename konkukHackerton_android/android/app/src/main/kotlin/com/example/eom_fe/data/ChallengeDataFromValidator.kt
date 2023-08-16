package com.example.eom_fe.data

data class ChallengeDataFromValidator(
    val id: Int,
    val title: String,
    val deadline: String,
    val totalProofNum: Int,
    val currentSuccessNum: Int,
    val achievementRate: Int,
    val challengeStatusCode: String,
    val validationIntervalCode: String,
    val validationCountPerInterval: Int,
    val categoryCode: String,
    val challengeHostName: String
) : java.io.Serializable
