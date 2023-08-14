package com.example.eom_fe.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\"\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\n\u001a\u00020\u000bH\'J\u0018\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0007\u001a\u00020\rH\'J\"\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u000fH\'J\"\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u0011H\'J\u0018\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\u0018\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\u0018\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\u0018\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\u0018\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\"\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u000fH\'J\"\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0019\u001a\u00020\u00062\b\b\u0001\u0010\u001a\u001a\u00020\u0006H\'J\u0018\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\u0018\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u001d\u001a\u00020\u000bH\'J\"\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u001f\u001a\u00020\u0006H\'J\u0018\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\u0018\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\u0018\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\u0018\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\u000e\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\'J\u0018\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\u0018\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\"\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010(\u001a\u00020\u000bH\'J\u0018\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\"\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020+H\'\u00a8\u0006,"}, d2 = {"Lcom/example/eom_fe/api/RetrofitService;", "", "addChallengeData", "Lretrofit2/Call;", "Lcom/example/eom_fe/data/APIResponseData;", "number", "", "params", "Lcom/example/eom_fe/data/ChallengeRequestData;", "addDailyPlan", "date", "", "addMemberData", "Lcom/example/eom_fe/data/MemberData;", "addTodoData", "Lcom/example/eom_fe/data/ToDoData;", "addValidatorData", "Lcom/example/eom_fe/data/ValidatorListData;", "completeTodoData", "deleteChallengeData", "deleteDailyPlan", "deleteTodoData", "editChallengeData", "editTodoData", "enterChallengeValidation", "cId", "mId", "failureTodoData", "findMemberData", "email", "followOtherMember", "otherNumber", "getAllChallenges", "getChallengeData", "getDailyPlan", "getDailyTodoData", "getFollowerList", "getFollwingList", "getMemberData", "getMontlyPlan", "yearMonth", "getTodoData", "renewFirebaseToken", "Lcom/example/eom_fe/data/FirebaseToken;", "app_debug"})
public abstract interface RetrofitService {
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "member")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> addMemberData(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.eom_fe.data.MemberData params);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "member/{memberId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> getMemberData(@retrofit2.http.Path(value = "memberId")
    int number);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "member/follow/{email}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> findMemberData(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Path(value = "email")
    java.lang.String email);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "member/firebaseToken/{memberId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> renewFirebaseToken(@retrofit2.http.Path(value = "memberId")
    int number, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.eom_fe.data.FirebaseToken params);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "follow/{memberId}/{followingMemberId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> followOtherMember(@retrofit2.http.Path(value = "memberId")
    int number, @retrofit2.http.Path(value = "followMemberId")
    int otherNumber);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "follow/followingList/{memberId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> getFollwingList(@retrofit2.http.Path(value = "memberId")
    int number);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "follow/followers")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> getFollowerList();
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "dailyplan/{memberId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> addDailyPlan(@retrofit2.http.Path(value = "memberId")
    int number, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "date")
    java.lang.String date);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "dailyplan/{dailyPlanId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> getDailyPlan(@retrofit2.http.Path(value = "dailyPlanId")
    int number);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.DELETE(value = "dailyplan/{dailyPlanId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> deleteDailyPlan(@retrofit2.http.Path(value = "dailyPlanId")
    int number);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "dailyplan/{memberId}/{yyyymm}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> getMontlyPlan(@retrofit2.http.Path(value = "memberId")
    int number, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Path(value = "yyyymm")
    java.lang.String yearMonth);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "todo/{dailyplanId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> addTodoData(@retrofit2.http.Path(value = "dailyplanId")
    int number, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.eom_fe.data.ToDoData params);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.PUT(value = "todo/{todoId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> editTodoData(@retrofit2.http.Path(value = "todoId")
    int number, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.eom_fe.data.ToDoData params);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "todo/{todoId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> getTodoData(@retrofit2.http.Path(value = "todoId")
    int number);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.DELETE(value = "todo/{todoId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> deleteTodoData(@retrofit2.http.Path(value = "todoId")
    int number);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "todo/{dailyplanId}/list")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> getDailyTodoData(@retrofit2.http.Path(value = "dailyplanId")
    int number);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "todo/complete/{todoId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> completeTodoData(@retrofit2.http.Path(value = "todoId")
    int number);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "todo/fail/{todoId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> failureTodoData(@retrofit2.http.Path(value = "todoId")
    int number);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "challenge/{memberId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> addChallengeData(@retrofit2.http.Path(value = "memberId")
    int number, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.eom_fe.data.ChallengeRequestData params);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "challenge/{challengeId}/validator")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> addValidatorData(@retrofit2.http.Path(value = "challengeId")
    int number, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.eom_fe.data.ValidatorListData params);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "challenge/{challengeId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> getChallengeData(@retrofit2.http.Path(value = "challengeId")
    int number);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "challenge/{memberId}/list")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> getAllChallenges(@retrofit2.http.Path(value = "memberId")
    int number);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.PUT(value = "challenge/{challengeId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> editChallengeData(@retrofit2.http.Path(value = "challengeId")
    int number);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.DELETE(value = "challenge/{challengeId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> deleteChallengeData(@retrofit2.http.Path(value = "challengeId")
    int number);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "validation/Entry/{challengeId}/{memberId}")
    public abstract retrofit2.Call<com.example.eom_fe.data.APIResponseData> enterChallengeValidation(@retrofit2.http.Path(value = "challengeId")
    int cId, @retrofit2.http.Path(value = "memberId")
    int mId);
}