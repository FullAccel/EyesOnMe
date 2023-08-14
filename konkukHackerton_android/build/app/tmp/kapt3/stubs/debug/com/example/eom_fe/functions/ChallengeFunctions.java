package com.example.eom_fe.functions;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J$\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0014\u0010\u001e\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010 \u0012\u0004\u0012\u00020\u001b0\u001fJ\u001c\u0010!\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020 2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$J\u0016\u0010&\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020\'J\u000e\u0010(\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020 J\u000e\u0010)\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0016\u0010*\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020 2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u0010+\u001a\u00020\u001bJ\u0019\u0010,\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010$H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010.J!\u0010/\u001a\n\u0012\u0004\u0012\u00020%\u0018\u00010$2\u0006\u0010\"\u001a\u00020 H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00100J\u001b\u00101\u001a\u0004\u0018\u00010-2\u0006\u0010\"\u001a\u00020 H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00100J\u000e\u00102\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020 J\u000e\u00103\u001a\u00020\u001b2\u0006\u00104\u001a\u00020\u0015J\u001c\u00105\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$J\u0006\u00106\u001a\u00020\u001bJ\u0006\u00107\u001a\u00020\u001bJ\u0006\u00108\u001a\u00020\u001bR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0012\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000bR\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00069"}, d2 = {"Lcom/example/eom_fe/functions/ChallengeFunctions;", "", "context", "Landroid/content/Context;", "applicationContext", "(Landroid/content/Context;Landroid/content/Context;)V", "alarmFunctions", "Lcom/example/eom_fe/alarm_package/AlarmFunctions;", "getAlarmFunctions", "()Lcom/example/eom_fe/alarm_package/AlarmFunctions;", "getApplicationContext", "()Landroid/content/Context;", "db", "Lcom/example/eom_fe/roomDB/AlarmDB;", "getDb", "()Lcom/example/eom_fe/roomDB/AlarmDB;", "eventChannel", "Lio/flutter/plugin/common/EventChannel;", "mainContext", "getMainContext", "memberInfo", "Lcom/example/eom_fe/data/MemberData;", "getMemberInfo", "()Lcom/example/eom_fe/data/MemberData;", "setMemberInfo", "(Lcom/example/eom_fe/data/MemberData;)V", "addChallengeDataFunc", "", "cd", "Lcom/example/eom_fe/data/ChallengeRequestData;", "callback", "Lkotlin/Function1;", "", "addValidatior", "cId", "vld", "", "", "addValidatorDataFunc", "Lcom/example/eom_fe/data/ValidatorListData;", "deleteChallengeDataFunc", "editChallenge", "editChallengeDataFunc", "getAllChallenges", "getAllChallengesFunc", "Lcom/example/eom_fe/data/ChallengeData;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllValidators", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getChallengeDataFunc", "getChallengeDataFunc2", "init", "memberData", "makeChallengeWithValidator", "requestValidation", "requestVerifyAgain", "uploadChallengePost", "app_debug"})
public final class ChallengeFunctions {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context mainContext = null;
    @org.jetbrains.annotations.NotNull
    private final android.content.Context applicationContext = null;
    @org.jetbrains.annotations.NotNull
    private com.example.eom_fe.data.MemberData memberInfo;
    private io.flutter.plugin.common.EventChannel eventChannel;
    @org.jetbrains.annotations.NotNull
    private final com.example.eom_fe.alarm_package.AlarmFunctions alarmFunctions = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.eom_fe.roomDB.AlarmDB db = null;
    
    public ChallengeFunctions(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    android.content.Context applicationContext) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.content.Context getMainContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.content.Context getApplicationContext() {
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
    public final com.example.eom_fe.alarm_package.AlarmFunctions getAlarmFunctions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.eom_fe.roomDB.AlarmDB getDb() {
        return null;
    }
    
    public final void init(@org.jetbrains.annotations.NotNull
    com.example.eom_fe.data.MemberData memberData) {
    }
    
    public final void getAllChallenges() {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getAllValidators(int cId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> continuation) {
        return null;
    }
    
    public final void addValidatior(int cId, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> vld) {
    }
    
    public final void requestVerifyAgain() {
    }
    
    public final void uploadChallengePost() {
    }
    
    public final void requestValidation() {
    }
    
    public final void makeChallengeWithValidator(@org.jetbrains.annotations.NotNull
    com.example.eom_fe.data.ChallengeRequestData cd, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> vld) {
    }
    
    public final void editChallenge(@org.jetbrains.annotations.NotNull
    com.example.eom_fe.data.ChallengeRequestData cd) {
    }
    
    public final void addChallengeDataFunc(@org.jetbrains.annotations.NotNull
    com.example.eom_fe.data.ChallengeRequestData cd, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> callback) {
    }
    
    public final void addValidatorDataFunc(int cId, @org.jetbrains.annotations.NotNull
    com.example.eom_fe.data.ValidatorListData vld) {
    }
    
    public final void getChallengeDataFunc2(int cId) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getChallengeDataFunc(int cId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.eom_fe.data.ChallengeData> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getAllChallengesFunc(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.eom_fe.data.ChallengeData>> continuation) {
        return null;
    }
    
    public final void editChallengeDataFunc(int cId, @org.jetbrains.annotations.NotNull
    com.example.eom_fe.data.ChallengeRequestData cd) {
    }
    
    public final void deleteChallengeDataFunc(int cId) {
    }
}