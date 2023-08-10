package com.example.eom_fe.data

data class ToDoData(
    val memberId: Int,
    val title: String,
    val alarmStartTime: String,
    val alarmEndTime: String,
    val categoryCode: String,
    val complete: Boolean
) : java.io.Serializable