package com.example.eom_fe.data

data class ToDoData(
    val id: Int,
    val title: String,
    val alarmStartTime: String,
    val alarmEndTime: String,
    val categoryCode: String,
    val _complete: Boolean
) : java.io.Serializable