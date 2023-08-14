package com.example.eom_fe.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\n\u001a\u00020\u000bH\'J\"\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\n\u001a\u00020\rH\'J\u0018\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\u0018\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\u0018\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\"\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\n\u001a\u00020\rH\'J\u0018\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\u0018\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0014\u001a\u00020\bH\'J\u001c\u0010\u0015\u001a\u00020\u00162\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0017\u001a\u00020\u0006H\'J\u0018\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\u0018\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\b\u0010\u001a\u001a\u00020\u0016H\'J\u0012\u0010\u001b\u001a\u00020\u00162\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\u0018\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\"\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u001e\u001a\u00020\bH\'J\u0018\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\"\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\n\u001a\u00020!H\'\u00a8\u0006\""}, d2 = {"Lcom/example/eom_fe/api/RetrofitService;", "", "addDailyPlan", "Lretrofit2/Call;", "Lcom/example/eom_fe/data/APIResponseData;", "number", "", "date", "", "addMemberData", "params", "Lcom/example/eom_fe/data/MemberData;", "addTodoData", "Lcom/example/eom_fe/data/ToDoData;", "completeTodoData", "deleteDailyPlan", "deleteTodoData", "editTodoData", "failureTodoData", "findMemberData", "email", "followOtherMember", "", "otherNumber", "getDailyPlan", "getDailyTodoData", "getFollowerList", "getFollwingList", "getMemberData", "getMontlyPlan", "yearMonth", "getTodoData", "renewFirebaseToken", "Lcom/example/eom_fe/data/FirebaseToken;", "app_debug"})
public abstract interface RetrofitService {
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "member")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> addMemberData(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.eom_fe.data.MemberData params);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.GET(value = "member/{memberId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> getMemberData(@retrofit2.http.Path(value = "memberId")
    int number);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.GET(value = "member/follow/{email}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> findMemberData(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Path(value = "email")
    java.lang.String email);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "member/firebaseToken/{memberId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> renewFirebaseToken(@retrofit2.http.Path(value = "memberId")
    int number, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.eom_fe.data.FirebaseToken params);
    
    @retrofit2.http.POST(value = "follow/{memberId}/{followingMemberId}")
    public abstract void followOtherMember(@retrofit2.http.Path(value = "memberId")
    int number, @retrofit2.http.Path(value = "followMemberId")
    int otherNumber);
    
    @retrofit2.http.GET(value = "follow/followingList/{memberId}")
    public abstract void getFollwingList(@retrofit2.http.Path(value = "memberId")
    int number);
    
    @retrofit2.http.GET(value = "follow/followers")
    public abstract void getFollowerList();
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "dailyplan/{memberId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> addDailyPlan(@retrofit2.http.Path(value = "memberId")
    int number, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Query(value = "date")
    java.lang.String date);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.GET(value = "dailyplan/{dailyPlanId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> getDailyPlan(@retrofit2.http.Path(value = "dailyPlanId")
    int number);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.DELETE(value = "dailyplan/{dailyPlanId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> deleteDailyPlan(@retrofit2.http.Path(value = "dailyPlanId")
    int number);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.GET(value = "dailyplan/{memberId}/{yyyymm}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> getMontlyPlan(@retrofit2.http.Path(value = "memberId")
    int number, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Path(value = "yyyymm")
    java.lang.String yearMonth);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "todo/{dailyplanId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> addTodoData(@retrofit2.http.Path(value = "dailyplanId")
    int number, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.eom_fe.data.ToDoData params);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.PUT(value = "todo/{todoId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> editTodoData(@retrofit2.http.Path(value = "todoId")
    int number, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.eom_fe.data.ToDoData params);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.GET(value = "todo/{todoId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> getTodoData(@retrofit2.http.Path(value = "todoId")
    int number);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.DELETE(value = "todo/{todoId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> deleteTodoData(@retrofit2.http.Path(value = "todoId")
    int number);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.GET(value = "todo/{dailyplanId}/list")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> getDailyTodoData(@retrofit2.http.Path(value = "dailyplanId")
    int number);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "todo/complete/{todoId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> completeTodoData(@retrofit2.http.Path(value = "todoId")
    int number);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "todo/fail/{todoId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> failureTodoData(@retrofit2.http.Path(value = "todoId")
    int number);
}