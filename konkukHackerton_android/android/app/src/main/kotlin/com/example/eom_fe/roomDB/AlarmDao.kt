package com.example.eom_fe.roomDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AlarmDao { // 재부팅 시 관리되어야 하는 알람 저장용 테이블 관련
    @Query("select * from active_alarms")
    fun getAllAlarms() : List<AlarmDataModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE) // 알람은 중복 허용
    fun addAlarm(item: AlarmDataModel)

    @Query("DELETE FROM active_alarms WHERE alarm_code = :alarm_code") // 알람 코드로 삭제
    fun deleteAlarm(alarm_code: Int)

    @Query("SELECT * FROM active_alarms WHERE alarm_code = :alarmCode")
    fun getSingleAlarm(alarmCode: Int): AlarmDataModel
}