package com.example.eom_fe.data

data class AddValidatorListData(
    val challengeId: Int,
    val validatorNameList: List<String>
) : java.io.Serializable
