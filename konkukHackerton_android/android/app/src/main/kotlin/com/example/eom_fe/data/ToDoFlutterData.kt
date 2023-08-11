package com.example.eom_fe.data

data class ToDoFlutterData(
    val toDoData: ToDoData,
    val isAlarm: Boolean,
    val alarmType: Int
) : java.io.Serializable
