package com.example.eom_fe.data

data class ChallengeMakeData(
    val challengeRequestData: ChallengeRequestData,
    val validatorNameList: List<String>?
) : java.io.Serializable