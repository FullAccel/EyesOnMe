package com.example.eom_fe.data

data class APIResponseData(
    val status: Int,
    val code: String,
    val message: String,
    val data: Any
) : java.io.Serializable
