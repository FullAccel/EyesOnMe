package com.example.eom_fe.functions;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J$\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0014\u0010\u001e\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010 \u0012\u0004\u0012\u00020\u001b0\u001fJ,\u0010!\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020$2\u0014\u0010\u001e\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010 \u0012\u0004\u0012\u00020\u001b0\u001fJ;\u0010%\u001a\b\u0012\u0004\u0012\u0002H\'0&\"\u0004\b\u0000\u0010\'2\u001c\u0010(\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\'0)\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001fH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010*J\u000e\u0010+\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020 J\u0010\u0010-\u001a\u00020\u001b2\u0006\u0010.\u001a\u00020 H\u0007J\u000e\u0010/\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020 J\u000e\u00100\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020 J&\u00101\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020$2\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u00020 2\u0006\u00105\u001a\u00020 J(\u00106\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u00104\u001a\u00020 2\u0006\u00107\u001a\u00020\u001d2\u0006\u00105\u001a\u00020 H\u0007J\u000e\u00108\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020 J$\u00109\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0014\u0010\u001e\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010 \u0012\u0004\u0012\u00020\u001b0\u001fJ\u000e\u0010:\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020 J\u001a\u0010;\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0=0<2\u0006\u0010\u001c\u001a\u00020\u001dJ!\u0010>\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010=2\u0006\u0010\"\u001a\u00020 H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010?J*\u0010@\u001a\u00020\u001b2\u0006\u0010A\u001a\u00020\u001d2\u001a\u0010\u001e\u001a\u0016\u0012\f\u0012\n\u0012\u0004\u0012\u00020B\u0018\u00010=\u0012\u0004\u0012\u00020\u001b0\u001fJ\u001b\u0010C\u001a\u0004\u0018\u00010D2\u0006\u0010\u001c\u001a\u00020\u001dH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010EJ\u000e\u0010F\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020 J\u001b\u0010G\u001a\u0004\u0018\u00010D2\u0006\u0010\u001c\u001a\u00020\u001dH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010EJ\u000e\u0010H\u001a\u00020\u001b2\u0006\u0010I\u001a\u00020\u0015J0\u0010J\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020$2\u0006\u0010K\u001a\u0002032\u0006\u00104\u001a\u00020 2\u0006\u00105\u001a\u00020 H\u0007J\u000e\u0010L\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ \u0010M\u001a\u00020\u001b2\u0006\u0010N\u001a\u00020 2\u0006\u0010O\u001a\u00020\u001d2\u0006\u00107\u001a\u00020\u001dH\u0003J \u0010P\u001a\u00020\u001b2\u0006\u00107\u001a\u00020\u001d2\u0006\u00104\u001a\u00020 2\u0006\u00105\u001a\u00020 H\u0007J \u0010Q\u001a\u00020\u001b2\u0006\u00107\u001a\u00020\u001d2\u0006\u00104\u001a\u00020 2\u0006\u00105\u001a\u00020 H\u0007R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0012\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000bR\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006R"}, d2 = {"Lcom/example/eom_fe/functions/DataFunctions;", "", "context", "Landroid/content/Context;", "applicationContext", "(Landroid/content/Context;Landroid/content/Context;)V", "alarmFunctions", "Lcom/example/eom_fe/alarm_package/AlarmFunctions;", "getAlarmFunctions", "()Lcom/example/eom_fe/alarm_package/AlarmFunctions;", "getApplicationContext", "()Landroid/content/Context;", "db", "Lcom/example/eom_fe/roomDB/AlarmDB;", "getDb", "()Lcom/example/eom_fe/roomDB/AlarmDB;", "eventChannel", "Lio/flutter/plugin/common/EventChannel;", "mainContext", "getMainContext", "memberInfo", "Lcom/example/eom_fe/data/MemberData;", "getMemberInfo", "()Lcom/example/eom_fe/data/MemberData;", "setMemberInfo", "(Lcom/example/eom_fe/data/MemberData;)V", "addDailyPlanFunc", "", "date", "", "callback", "Lkotlin/Function1;", "", "addTodoDataFunc", "dailyPlanId", "todo", "Lcom/example/eom_fe/data/ToDoData;", "asyncWithDeferred", "Lkotlinx/coroutines/CompletableDeferred;", "T", "block", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "completeTodoDataFunc", "todoId", "delayAlarm", "aCode", "deleteDailyPlanFunc", "deleteTodoDataFunc", "editTodoDataFunc", "isAlarm", "", "aType", "aRepeat", "editWSAlarm", "time", "failureTodoDataFunc", "findDailyPlanIdFunc", "getDailyPlanFunc", "getDailyPlansByDate", "Lkotlinx/coroutines/flow/Flow;", "", "getDailyTodoDataFunc", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMonthlyPlanFunc", "yearMonth", "Lcom/example/eom_fe/data/DailyPlanData;", "getSleepAlarm", "Lcom/example/eom_fe/roomDB/AlarmDataModel;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTodoDataFunc", "getWakeAlarm", "init", "memberData", "postTodoDataFunc", "alarm", "runDailyPlansByDate", "setAlarm", "alarmCode", "content", "setSleepAlarm", "setWakeAlarm", "app_debug"})
public final class DataFunctions {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context mainContext = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context applicationContext = null;
    @org.jetbrains.annotations.NotNull()
    private com.example.eom_fe.data.MemberData memberInfo;
    private io.flutter.plugin.common.EventChannel eventChannel;
    @org.jetbrains.annotations.NotNull()
    private final com.example.eom_fe.alarm_package.AlarmFunctions alarmFunctions = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.eom_fe.roomDB.AlarmDB db = null;
    
    public DataFunctions(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.content.Context applicationContext) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getMainContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getApplicationContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.eom_fe.data.MemberData getMemberInfo() {
        return null;
    }
    
    public final void setMemberInfo(@org.jetbrains.annotations.NotNull()
    com.example.eom_fe.data.MemberData p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.eom_fe.alarm_package.AlarmFunctions getAlarmFunctions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.eom_fe.roomDB.AlarmDB getDb() {
        return null;
    }
    
    public final void init(@org.jetbrains.annotations.NotNull()
    com.example.eom_fe.data.MemberData memberData) {
    }
    
    public final void addDailyPlanFunc(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> callback) {
    }
    
    public final void getDailyPlanFunc(int dailyPlanId) {
    }
    
    public final void deleteDailyPlanFunc(int dailyPlanId) {
    }
    
    public final void getMonthlyPlanFunc(@org.jetbrains.annotations.NotNull()
    java.lang.String yearMonth, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<com.example.eom_fe.data.DailyPlanData>, kotlin.Unit> callback) {
    }
    
    public final void findDailyPlanIdFunc(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> callback) {
    }
    
    private final <T extends java.lang.Object>java.lang.Object asyncWithDeferred(kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object> block, kotlin.coroutines.Continuation<? super kotlinx.coroutines.CompletableDeferred<T>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.eom_fe.data.ToDoData>> getDailyPlansByDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date) {
        return null;
    }
    
    public final void runDailyPlansByDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date) {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.M)
    public final void postTodoDataFunc(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    com.example.eom_fe.data.ToDoData todo, boolean alarm, int aType, int aRepeat) {
    }
    
    public final void addTodoDataFunc(int dailyPlanId, @org.jetbrains.annotations.NotNull()
    com.example.eom_fe.data.ToDoData todo, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> callback) {
    }
    
    public final void editTodoDataFunc(@org.jetbrains.annotations.NotNull()
    com.example.eom_fe.data.ToDoData todo, boolean isAlarm, int aType, int aRepeat) {
    }
    
    public final void getTodoDataFunc(int todoId) {
    }
    
    public final void deleteTodoDataFunc(int todoId) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getDailyTodoDataFunc(int dailyPlanId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.eom_fe.data.ToDoData>> continuation) {
        return null;
    }
    
    public final void completeTodoDataFunc(int todoId) {
    }
    
    public final void failureTodoDataFunc(int todoId) {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.M)
    private final void setAlarm(int alarmCode, java.lang.String content, java.lang.String time) {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.M)
    public final void setWakeAlarm(@org.jetbrains.annotations.NotNull()
    java.lang.String time, int aType, int aRepeat) {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.M)
    public final void setSleepAlarm(@org.jetbrains.annotations.NotNull()
    java.lang.String time, int aType, int aRepeat) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getWakeAlarm(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.eom_fe.roomDB.AlarmDataModel> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getSleepAlarm(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.eom_fe.roomDB.AlarmDataModel> continuation) {
        return null;
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.M)
    public final void editWSAlarm(@org.jetbrains.annotations.NotNull()
    java.lang.String date, int aType, @org.jetbrains.annotations.NotNull()
    java.lang.String time, int aRepeat) {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.O)
    public final void delayAlarm(int aCode) {
    }
}