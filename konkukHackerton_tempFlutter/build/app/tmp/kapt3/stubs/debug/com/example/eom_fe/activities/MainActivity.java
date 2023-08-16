package com.example.eom_fe.activities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 82\u00020\u0001:\u00018B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010/\u001a\u0002002\b\b\u0001\u00101\u001a\u000202H\u0017J\b\u00103\u001a\u000200H\u0002J\b\u00104\u001a\u000200H\u0015J\u0010\u00105\u001a\u0002002\u0006\u00106\u001a\u000207H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR*\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u0010j\b\u0012\u0004\u0012\u00020\u0011`\u0012X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u001eX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020$X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b\'\u0010(R\u001a\u0010)\u001a\u00020*X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.\u00a8\u00069"}, d2 = {"Lcom/example/eom_fe/activities/MainActivity;", "Lio/flutter/embedding/android/FlutterActivity;", "()V", "CHANNEL", "", "alarmFunctions", "Lcom/example/eom_fe/alarm_package/AlarmFunctions;", "getAlarmFunctions", "()Lcom/example/eom_fe/alarm_package/AlarmFunctions;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "getCoroutineScope", "()Lkotlinx/coroutines/CoroutineScope;", "coroutineScope$delegate", "Lkotlin/Lazy;", "data", "Ljava/util/ArrayList;", "Lcom/example/eom_fe/roomDB/AlarmDataModel;", "Lkotlin/collections/ArrayList;", "getData", "()Ljava/util/ArrayList;", "setData", "(Ljava/util/ArrayList;)V", "dataFunctions", "Lcom/example/eom_fe/functions/DataFunctions;", "getDataFunctions", "()Lcom/example/eom_fe/functions/DataFunctions;", "setDataFunctions", "(Lcom/example/eom_fe/functions/DataFunctions;)V", "db", "Lcom/example/eom_fe/roomDB/AlarmDB;", "getDb", "()Lcom/example/eom_fe/roomDB/AlarmDB;", "setDb", "(Lcom/example/eom_fe/roomDB/AlarmDB;)V", "loginFunctions", "Lcom/example/eom_fe/functions/LoginFunctions;", "getLoginFunctions", "()Lcom/example/eom_fe/functions/LoginFunctions;", "setLoginFunctions", "(Lcom/example/eom_fe/functions/LoginFunctions;)V", "memberInfo", "Lcom/example/eom_fe/data/MemberData;", "getMemberInfo", "()Lcom/example/eom_fe/data/MemberData;", "setMemberInfo", "(Lcom/example/eom_fe/data/MemberData;)V", "configureFlutterEngine", "", "flutterEngine", "Lio/flutter/embedding/engine/FlutterEngine;", "initLogin", "onStart", "onWindowFocusChanged", "hasFocus", "", "Companion", "app_debug"})
public final class MainActivity extends io.flutter.embedding.android.FlutterActivity {
    @org.jetbrains.annotations.NotNull
    public static final com.example.eom_fe.activities.MainActivity.Companion Companion = null;
    @org.jetbrains.annotations.Nullable
    private static java.lang.Integer memberId;
    @org.jetbrains.annotations.Nullable
    private static com.example.eom_fe.data.MemberData mInfo;
    @org.jetbrains.annotations.Nullable
    private static android.content.Context mContext;
    private final java.lang.String CHANNEL = "samples.flutter.dev/battery";
    private final kotlin.Lazy coroutineScope$delegate = null;
    @org.jetbrains.annotations.NotNull
    private java.util.ArrayList<com.example.eom_fe.roomDB.AlarmDataModel> data;
    public com.example.eom_fe.roomDB.AlarmDB db;
    @org.jetbrains.annotations.NotNull
    private final com.example.eom_fe.alarm_package.AlarmFunctions alarmFunctions = null;
    public com.example.eom_fe.data.MemberData memberInfo;
    public com.example.eom_fe.functions.LoginFunctions loginFunctions;
    public com.example.eom_fe.functions.DataFunctions dataFunctions;
    
    public MainActivity() {
        super();
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
    
    @org.jetbrains.annotations.NotNull
    public final com.example.eom_fe.data.MemberData getMemberInfo() {
        return null;
    }
    
    public final void setMemberInfo(@org.jetbrains.annotations.NotNull
    com.example.eom_fe.data.MemberData p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.eom_fe.functions.LoginFunctions getLoginFunctions() {
        return null;
    }
    
    public final void setLoginFunctions(@org.jetbrains.annotations.NotNull
    com.example.eom_fe.functions.LoginFunctions p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.eom_fe.functions.DataFunctions getDataFunctions() {
        return null;
    }
    
    public final void setDataFunctions(@org.jetbrains.annotations.NotNull
    com.example.eom_fe.functions.DataFunctions p0) {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.O)
    @java.lang.Override
    protected void onStart() {
    }
    
    @java.lang.Override
    public void onWindowFocusChanged(boolean hasFocus) {
    }
    
    private final void initLogin() {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.M)
    @java.lang.Override
    public void configureFlutterEngine(@org.jetbrains.annotations.NotNull
    @androidx.annotation.NonNull
    io.flutter.embedding.engine.FlutterEngine flutterEngine) {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0015\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0016"}, d2 = {"Lcom/example/eom_fe/activities/MainActivity$Companion;", "", "()V", "mContext", "Landroid/content/Context;", "getMContext", "()Landroid/content/Context;", "setMContext", "(Landroid/content/Context;)V", "mInfo", "Lcom/example/eom_fe/data/MemberData;", "getMInfo", "()Lcom/example/eom_fe/data/MemberData;", "setMInfo", "(Lcom/example/eom_fe/data/MemberData;)V", "memberId", "", "getMemberId", "()Ljava/lang/Integer;", "setMemberId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Integer getMemberId() {
            return null;
        }
        
        public final void setMemberId(@org.jetbrains.annotations.Nullable
        java.lang.Integer p0) {
        }
        
        @org.jetbrains.annotations.Nullable
        public final com.example.eom_fe.data.MemberData getMInfo() {
            return null;
        }
        
        public final void setMInfo(@org.jetbrains.annotations.Nullable
        com.example.eom_fe.data.MemberData p0) {
        }
        
        @org.jetbrains.annotations.Nullable
        public final android.content.Context getMContext() {
            return null;
        }
        
        public final void setMContext(@org.jetbrains.annotations.Nullable
        android.content.Context p0) {
        }
    }
}