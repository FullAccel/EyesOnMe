package com.example.eom_fe.data

data class DailyPlanData(
    val yearMonth: String,
    val date: Int,
    val toDoListResponseDtos: ArrayList<ToDoData>
) : java.io.Serializable
