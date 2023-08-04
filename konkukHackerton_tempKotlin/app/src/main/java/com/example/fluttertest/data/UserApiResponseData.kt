package com.example.fluttertest.data

data class UserApiResponseData(
    val status: Int,
    val code: String,
    val message: String,
    val data: MemberData
) : java.io.Serializable
