package com.example.eom_fe.activities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0016\u001a\u00020\u00172\b\b\u0001\u0010\u0018\u001a\u00020\u0019H\u0017J\b\u0010\u001a\u001a\u00020\u0017H\u0016J\u0012\u0010\u001b\u001a\u00020\u00172\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\u0010\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\u0011H\u0014J\b\u0010 \u001a\u00020\u0017H\u0014J\b\u0010!\u001a\u00020\u0017H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u00020\u0011X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015\u00a8\u0006\""}, d2 = {"Lcom/example/eom_fe/activities/BackgroundActivity;", "Lio/flutter/embedding/android/FlutterActivity;", "()V", "CHANNEL1", "", "CHANNEL2", "connection", "Landroid/content/ServiceConnection;", "isFinished", "", "()Z", "setFinished", "(Z)V", "mBound", "mService", "Lcom/example/eom_fe/follow/KeepService;", "serviceIntent", "Landroid/content/Intent;", "getServiceIntent", "()Landroid/content/Intent;", "setServiceIntent", "(Landroid/content/Intent;)V", "configureFlutterEngine", "", "flutterEngine", "Lio/flutter/embedding/engine/FlutterEngine;", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onNewIntent", "intent", "onStart", "onStop", "app_debug"})
public final class BackgroundActivity extends io.flutter.embedding.android.FlutterActivity {
    private final java.lang.String CHANNEL1 = "samples.flutter.dev/battery";
    public android.content.Intent serviceIntent;
    private boolean isFinished = false;
    private final java.lang.String CHANNEL2 = "samples.flutter.dev/accomplish";
    private com.example.eom_fe.follow.KeepService mService;
    private boolean mBound = false;
    
    /**
     * Defines callbacks for service binding, passed to bindService()
     */
    private final android.content.ServiceConnection connection = null;
    
    public BackgroundActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Intent getServiceIntent() {
        return null;
    }
    
    public final void setServiceIntent(@org.jetbrains.annotations.NotNull()
    android.content.Intent p0) {
    }
    
    public final boolean isFinished() {
        return false;
    }
    
    public final void setFinished(boolean p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onNewIntent(@org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.M)
    @java.lang.Override()
    public void configureFlutterEngine(@org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    io.flutter.embedding.engine.FlutterEngine flutterEngine) {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    @java.lang.Override()
    protected void onStart() {
    }
    
    @java.lang.Override()
    protected void onStop() {
    }
}