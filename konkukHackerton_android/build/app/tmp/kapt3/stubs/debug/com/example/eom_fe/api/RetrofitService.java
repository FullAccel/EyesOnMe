package com.example.eom_fe.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0004H\'J\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\n\u001a\u00020\u000bH\'\u00a8\u0006\f"}, d2 = {"Lcom/example/eom_fe/api/RetrofitService;", "", "addMemberData", "Lretrofit2/Call;", "Lcom/example/eom_fe/data/MemberData;", "params", "findMemberData", "email", "", "getMemberData", "number", "", "app_debug"})
public abstract interface RetrofitService {
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "member")
    public abstract retrofit2.Call<com.example.eom_fe.data.MemberData> addMemberData(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.eom_fe.data.MemberData params);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "member/{memberId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.MemberData> getMemberData(@retrofit2.http.Path(value = "memberId")
    int number);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "member/{email}")
    public abstract retrofit2.Call<com.example.eom_fe.data.MemberData> findMemberData(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Path(value = "email")
    java.lang.String email);
}