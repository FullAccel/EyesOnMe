package com.example.eom_fe.functions;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J3\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000f2!\u0010\u001d\u001a\u001d\u0012\u0013\u0012\u00110\u000f\u00a2\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u001b0\u001eH\u0002J5\u0010\"\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020\u00152#\u0010\u001d\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u000f\u00a2\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u001b0\u001eH\u0002J\u000e\u0010$\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015J\u0019\u0010%\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010&R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\'"}, d2 = {"Lcom/example/eom_fe/functions/LoginFunctions;", "", "context", "Landroid/content/Context;", "applicationContext", "(Landroid/content/Context;Landroid/content/Context;)V", "getApplicationContext", "()Landroid/content/Context;", "dataFunctions", "Lcom/example/eom_fe/functions/DataFunctions;", "getDataFunctions", "()Lcom/example/eom_fe/functions/DataFunctions;", "mainContext", "getMainContext", "memberInfo", "Lcom/example/eom_fe/data/MemberData;", "getMemberInfo", "()Lcom/example/eom_fe/data/MemberData;", "setMemberInfo", "(Lcom/example/eom_fe/data/MemberData;)V", "memberToken", "", "getMemberToken", "()Ljava/lang/String;", "setMemberToken", "(Ljava/lang/String;)V", "createMemberInfo", "", "memberData", "callback", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "createdMemberInfo", "findLoginInfoHttp", "email", "kakaoLogin", "kakaoLogin2", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class LoginFunctions {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context mainContext = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context applicationContext = null;
    @org.jetbrains.annotations.NotNull()
    private com.example.eom_fe.data.MemberData memberInfo;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String memberToken = "";
    @org.jetbrains.annotations.NotNull()
    private final com.example.eom_fe.functions.DataFunctions dataFunctions = null;
    
    public LoginFunctions(@org.jetbrains.annotations.NotNull()
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
    public final java.lang.String getMemberToken() {
        return null;
    }
    
    public final void setMemberToken(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.eom_fe.functions.DataFunctions getDataFunctions() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object kakaoLogin2(@org.jetbrains.annotations.NotNull()
    java.lang.String memberToken, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.eom_fe.data.MemberData> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.eom_fe.data.MemberData kakaoLogin(@org.jetbrains.annotations.NotNull()
    java.lang.String memberToken) {
        return null;
    }
    
    private final void findLoginInfoHttp(java.lang.String email, kotlin.jvm.functions.Function1<? super com.example.eom_fe.data.MemberData, kotlin.Unit> callback) {
    }
    
    private final void createMemberInfo(com.example.eom_fe.data.MemberData memberData, kotlin.jvm.functions.Function1<? super com.example.eom_fe.data.MemberData, kotlin.Unit> callback) {
    }
}