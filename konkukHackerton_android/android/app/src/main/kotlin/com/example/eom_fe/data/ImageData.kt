package com.example.eom_fe.data

data class ImageData(
    val id: Int,
    val originName: String,
    val storedName: String,
    val accessUrl: String
) : java.io.Serializable
