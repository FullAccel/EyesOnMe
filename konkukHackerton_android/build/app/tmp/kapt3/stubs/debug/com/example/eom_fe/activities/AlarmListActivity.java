package com.example.eom_fe.activities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\'\u001a\u00020(J\b\u0010)\u001a\u00020(H\u0007J\u0012\u0010*\u001a\u00020(2\b\u0010+\u001a\u0004\u0018\u00010,H\u0014J \u0010-\u001a\u00020(2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u000201H\u0003R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R*\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u00150\u0014j\b\u0012\u0004\u0012\u00020\u0015`\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\"X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&\u00a8\u00063"}, d2 = {"Lcom/example/eom_fe/activities/AlarmListActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/example/eom_fe/alarm_package/AlarmRecyclerAdapter;", "getAdapter", "()Lcom/example/eom_fe/alarm_package/AlarmRecyclerAdapter;", "setAdapter", "(Lcom/example/eom_fe/alarm_package/AlarmRecyclerAdapter;)V", "alarmFunctions", "Lcom/example/eom_fe/alarm_package/AlarmFunctions;", "getAlarmFunctions", "()Lcom/example/eom_fe/alarm_package/AlarmFunctions;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "getCoroutineScope", "()Lkotlinx/coroutines/CoroutineScope;", "coroutineScope$delegate", "Lkotlin/Lazy;", "data", "Ljava/util/ArrayList;", "Lcom/example/eom_fe/roomDB/AlarmDataModel;", "Lkotlin/collections/ArrayList;", "getData", "()Ljava/util/ArrayList;", "setData", "(Ljava/util/ArrayList;)V", "db", "Lcom/example/eom_fe/roomDB/AlarmDB;", "getDb", "()Lcom/example/eom_fe/roomDB/AlarmDB;", "setDb", "(Lcom/example/eom_fe/roomDB/AlarmDB;)V", "listBinding", "Lcom/example/eom_fe/databinding/ActivityAlarmListBinding;", "getListBinding", "()Lcom/example/eom_fe/databinding/ActivityAlarmListBinding;", "setListBinding", "(Lcom/example/eom_fe/databinding/ActivityAlarmListBinding;)V", "getAllAlarm", "", "init", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setAlarm", "alarmCode", "", "content", "", "time", "app_debug"})
public final class AlarmListActivity extends androidx.appcompat.app.AppCompatActivity {
    public com.example.eom_fe.databinding.ActivityAlarmListBinding listBinding;
    public com.example.eom_fe.alarm_package.AlarmRecyclerAdapter adapter;
    private final kotlin.Lazy coroutineScope$delegate = null;
    @org.jetbrains.annotations.NotNull
    private java.util.ArrayList<com.example.eom_fe.roomDB.AlarmDataModel> data;
    public com.example.eom_fe.roomDB.AlarmDB db;
    @org.jetbrains.annotations.NotNull
    private final com.example.eom_fe.alarm_package.AlarmFunctions alarmFunctions = null;
    
    public AlarmListActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.eom_fe.databinding.ActivityAlarmListBinding getListBinding() {
        return null;
    }
    
    public final void setListBinding(@org.jetbrains.annotations.NotNull
    com.example.eom_fe.databinding.ActivityAlarmListBinding p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.eom_fe.alarm_package.AlarmRecyclerAdapter getAdapter() {
        return null;
    }
    
    public final void setAdapter(@org.jetbrains.annotations.NotNull
    com.example.eom_fe.alarm_package.AlarmRecyclerAdapter p0) {
    }
    
    private final kotlinx.coroutines.CoroutineScope getCoroutineScope() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.ArrayList<com.example.eom_fe.roomDB.AlarmDataModel> getData() {
        return null;
    }
    
    public final void setData(@org.jetbrains.annotations.NotNull
    java.util.ArrayList<com.example.eom_fe.roomDB.AlarmDataModel> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.eom_fe.roomDB.AlarmDB getDb() {
        return null;
    }
    
    public final void setDb(@org.jetbrains.annotations.NotNull
    com.example.eom_fe.roomDB.AlarmDB p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.eom_fe.alarm_package.AlarmFunctions getAlarmFunctions() {
        return null;
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.M)
    public final void init() {
    }
    
    public final void getAllAlarm() {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.M)
    private final void setAlarm(int alarmCode, java.lang.String content, java.lang.String time) {
    }
}