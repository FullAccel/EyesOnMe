package com.example.eom_fe.functions;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J3\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000b2!\u0010\u0019\u001a\u001d\u0012\u0013\u0012\u00110\u000b\u00a2\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u00170\u001aH\u0002J5\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\u00112#\u0010\u0019\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u000b\u00a2\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00170\u001aH\u0002J\u000e\u0010 \u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015\u00a8\u0006!"}, d2 = {"Lcom/example/eom_fe/functions/LoginFunctions;", "", "context", "Landroid/content/Context;", "applicationContext", "(Landroid/content/Context;Landroid/content/Context;)V", "getApplicationContext", "()Landroid/content/Context;", "mainContext", "getMainContext", "memberInfo", "Lcom/example/eom_fe/data/MemberData;", "getMemberInfo", "()Lcom/example/eom_fe/data/MemberData;", "setMemberInfo", "(Lcom/example/eom_fe/data/MemberData;)V", "memberToken", "", "getMemberToken", "()Ljava/lang/String;", "setMemberToken", "(Ljava/lang/String;)V", "createMemberInfo", "", "memberData", "callback", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "createdMemberInfo", "findLoginInfoHttp", "email", "kakaoLogin", "app_debug"})
public final class LoginFunctions {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context mainContext = null;
    @org.jetbrains.annotations.NotNull
    private final android.content.Context applicationContext = null;
    @org.jetbrains.annotations.NotNull
    private com.example.eom_fe.data.MemberData memberInfo;
    @org.jetbrains.annotations.NotNull
    private java.lang.String memberToken = "";
    
    public LoginFunctions(@org.jetbrains.annotations.NotNull
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
    public final java.lang.String getMemberToken() {
        return null;
    }
    
    public final void setMemberToken(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.eom_fe.data.MemberData kakaoLogin(@org.jetbrains.annotations.NotNull
    java.lang.String memberToken) {
        return null;
    }
    
    private final void findLoginInfoHttp(java.lang.String email, kotlin.jvm.functions.Function1<? super com.example.eom_fe.data.MemberData, kotlin.Unit> callback) {
    }
    
    private final void createMemberInfo(com.example.eom_fe.data.MemberData memberData, kotlin.jvm.functions.Function1<? super com.example.eom_fe.data.MemberData, kotlin.Unit> callback) {
    }
}