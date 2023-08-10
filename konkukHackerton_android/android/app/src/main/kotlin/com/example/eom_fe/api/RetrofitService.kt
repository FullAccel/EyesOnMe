package com.example.eom_fe.api

import com.example.eom_fe.data.APIResponseData
import com.example.eom_fe.data.MemberData
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    // Member
    @POST("member")
    fun addMemberData(@Body params: MemberData): Call<APIResponseData>

    @GET("member/{memberId}")
    fun getMemberData(@Path("memberId") number: Int): Call<APIResponseData>

    @GET("member/follow/{email}")
    fun findMemberData(@Path("email") email: String): Call<APIResponseData>


    // Follow
    @POST("follow/{memberId}/{followingMemberId}")
    fun followOtherMember(
        @Path("memberId") number: Int,
        @Path("followMemberId") otherNumber: Int
    )

    @GET("follow/followingList/{memberId}")
    fun getFollwingList(@Path("memberId") number: Int)

    @GET("follow/followers")
    fun getFollowerList()


    // DailyPlan
    @POST("dailyplan/{memberId}")
    fun addDailyPlan(
        @Path("memberId") number: Int,
        @Query("date") date: String
    ): Call<APIResponseData>

    @GET("dailyplan/{dailyPlanId}")
    fun getDailyPlan(
        @Path("dailyPlanId") number: Int
    ): Call<APIResponseData>

    @DELETE("dailyplan/{dailyPlanId}")
    fun deleteDailyPlan(
        @Path("dailyPlanId") number: Int
    ): Call<APIResponseData>

    @GET("dailyplan/{memberId}/month")
    fun getSingleDailyPlan(
        // 데일리 플랜 월별로 가져오기
        @Path("memberId") number: Int
    ): Call<APIResponseData>


    // ToDo

    @POST("todo/{dailyplanId}")
    fun addTodoData(@Path("dailyplanId") number: Int): Call<APIResponseData>

    @PUT("todo/{todoId}")
    fun editTodoData(@Path("todoId") number: Int): Call<APIResponseData>

    @GET("todo/{todoId}")
    fun getTodoData(@Path("todoId") number: Int): Call<APIResponseData>

    @DELETE("todo/{todoId")
    fun deleteTodoData(@Path("todoId") number: Int): Call<APIResponseData>

    @GET("todo/{dailyplanId}/list")
    fun getAllDailyTodoData(@Path("dailyplanId") number: Int): Call<APIResponseData>




}