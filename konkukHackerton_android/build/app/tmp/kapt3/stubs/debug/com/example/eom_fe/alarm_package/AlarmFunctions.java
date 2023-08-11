package com.example.eom_fe.alarm_package;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J \u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0010H\u0007J\u000e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012J\b\u0010\u0015\u001a\u00020\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/example/eom_fe/alarm_package/AlarmFunctions;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "ioScope", "Lkotlinx/coroutines/CoroutineScope;", "getIoScope", "()Lkotlinx/coroutines/CoroutineScope;", "ioScope$delegate", "Lkotlin/Lazy;", "pendingIntent", "Landroid/app/PendingIntent;", "callAlarm", "", "time", "", "alarm_code", "", "content", "cancelAlarm", "playSound", "app_debug"})
public final class AlarmFunctions {
    private final android.content.Context context = null;
    private android.app.PendingIntent pendingIntent;
    private final kotlin.Lazy ioScope$delegate = null;
    
    public AlarmFunctions(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    private final kotlinx.coroutines.CoroutineScope getIoScope() {
        return null;
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.M)
    public final void callAlarm(@org.jetbrains.annotations.NotNull
    java.lang.String time, int alarm_code, @org.jetbrains.annotations.NotNull
    java.lang.String content) {
    }
    
    public final void cancelAlarm(int alarm_code) {
    }
    
    private final void playSound() {
    }
}