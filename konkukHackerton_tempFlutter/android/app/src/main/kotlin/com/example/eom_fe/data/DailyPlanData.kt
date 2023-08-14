package com.example.eom_fe.data

data class DailyPlanData(
    val dailyPlanId: Int,
    val yearMonth: String,
    val date: Int,
    val toDoListResponseDtos: List<ToDoData>
) : java.io.Serializable
