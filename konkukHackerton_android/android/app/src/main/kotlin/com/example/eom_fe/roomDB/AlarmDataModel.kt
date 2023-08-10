package com.example.eom_fe.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "active_alarms") // 재부팅 시 활성화 되어야하는 알람 테이블
data class AlarmDataModel(
    @PrimaryKey(autoGenerate = true)
    var serialNum: Int, // 일련 번호
    var alarm_code : Int, // 알람 요청코드 -> 투두의 id랑 똑같이 가는 게 좋을 듯
    var time : String, // 시간
    var content : String // 알람 내용
)