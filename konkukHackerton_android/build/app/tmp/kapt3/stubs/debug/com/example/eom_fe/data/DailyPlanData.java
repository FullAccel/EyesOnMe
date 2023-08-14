package com.example.eom_fe.data;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00c6\u0003J7\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00c6\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u00d6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u001c\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u001d"}, d2 = {"Lcom/example/eom_fe/data/DailyPlanData;", "Ljava/io/Serializable;", "dailyPlanId", "", "yearMonth", "", "date", "toDoListResponseDtos", "", "Lcom/example/eom_fe/data/ToDoData;", "(ILjava/lang/String;ILjava/util/List;)V", "getDailyPlanId", "()I", "getDate", "getToDoListResponseDtos", "()Ljava/util/List;", "getYearMonth", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "toString", "app_debug"})
public final class DailyPlanData implements java.io.Serializable {
    private final int dailyPlanId = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String yearMonth = null;
    private final int date = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.example.eom_fe.data.ToDoData> toDoListResponseDtos = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.eom_fe.data.DailyPlanData copy(int dailyPlanId, @org.jetbrains.annotations.NotNull()
    java.lang.String yearMonth, int date, @org.jetbrains.annotations.NotNull()
    java.util.List<com.example.eom_fe.data.ToDoData> toDoListResponseDtos) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    public DailyPlanData(int dailyPlanId, @org.jetbrains.annotations.NotNull()
    java.lang.String yearMonth, int date, @org.jetbrains.annotations.NotNull()
    java.util.List<com.example.eom_fe.data.ToDoData> toDoListResponseDtos) {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int getDailyPlanId() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getYearMonth() {
        return null;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final int getDate() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.eom_fe.data.ToDoData> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.eom_fe.data.ToDoData> getToDoListResponseDtos() {
        return null;
    }
}