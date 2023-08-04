package com.example.fluttertest.data

import java.io.Serializable

data class MemberData(
    val id: Int,
    val name: String,
    val email: String,
    val profileUrl: String,
    val planSuccessCount: Int,
    val challengeSuccessCount: Int
)
    : Serializable
