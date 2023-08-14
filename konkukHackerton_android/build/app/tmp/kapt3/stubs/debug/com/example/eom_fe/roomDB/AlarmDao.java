package com.example.eom_fe.roomDB;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\'J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nH\'J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\bH\'\u00a8\u0006\r"}, d2 = {"Lcom/example/eom_fe/roomDB/AlarmDao;", "", "addAlarm", "", "item", "Lcom/example/eom_fe/roomDB/AlarmDataModel;", "deleteAlarm", "alarm_code", "", "getAllAlarms", "", "getSingleAlarm", "alarmCode", "app_debug"})
public abstract interface AlarmDao {
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "select * from active_alarms")
    public abstract java.util.List<com.example.eom_fe.roomDB.AlarmDataModel> getAllAlarms();
    
    @androidx.room.Insert(onConflict = 1)
    public abstract void addAlarm(@org.jetbrains.annotations.NotNull()
    com.example.eom_fe.roomDB.AlarmDataModel item);
    
    @androidx.room.Query(value = "DELETE FROM active_alarms WHERE alarm_code = :alarm_code")
    public abstract void deleteAlarm(int alarm_code);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM active_alarms WHERE alarm_code = :alarmCode")
    public abstract com.example.eom_fe.roomDB.AlarmDataModel getSingleAlarm(int alarmCode);
}