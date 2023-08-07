package com.example.eom_fe.data

import java.io.Serializable

data class UserApiResponseData(
    val status: Int,
    val code: String,
    val message: String,
    val data: MemberData
) : java.io.Serializable
