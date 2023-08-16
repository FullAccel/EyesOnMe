package com.example.eom_fe.api

import com.example.eom_fe.data.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
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

    @POST("member/firebaseToken/{memberId}")
    fun renewFirebaseToken(@Path("memberId") number: Int, @Body params: FirebaseToken): Call<APIResponseData>


    // Follow
    @POST("follow/{memberId}/{followingMemberId}")
    fun followOtherMember(
        @Path("memberId") number: Int,
        @Path("followMemberId") otherNumber: Int
    ): Call<APIResponseData>

    @GET("follow/followingList/{memberId}")
    fun getFollwingList(@Path("memberId") number: Int): Call<APIResponseData>

    @GET("follow/followers")
    fun getFollowerList(): Call<APIResponseData>


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

    @GET("dailyplan/{memberId}/{yyyymm}")
    fun getMontlyPlan(
        // 데일리 플랜 월별로 가져오기
        @Path("memberId") number: Int,
        @Path("yyyymm") yearMonth: String
    ): Call<APIResponseData>


    // ToDo

    @POST("todo/{dailyplanId}")
    fun addTodoData(@Path("dailyplanId") number: Int, @Body params: ToDoData): Call<APIResponseData>

    @PUT("todo/{todoId}")
    fun editTodoData(@Path("todoId") number: Int, @Body params: ToDoData): Call<APIResponseData>

    @GET("todo/{todoId}")
    fun getTodoData(@Path("todoId") number: Int): Call<APIResponseData>

    @DELETE("todo/{todoId}")
    fun deleteTodoData(@Path("todoId") number: Int): Call<APIResponseData>

    @GET("todo/{dailyplanId}/list")
    fun getDailyTodoData(@Path("dailyplanId") number: Int): Call<APIResponseData>

    @POST("todo/complete/{todoId}")
    fun completeTodoData(@Path("todoId") number: Int): Call<APIResponseData>

    @POST("todo/fail/{todoId}")
    fun failureTodoData(@Path("todoId") number: Int): Call<APIResponseData>


    // Challenge
    @POST("challenge/{memberId}")
    fun addChallengeData(@Path("memberId") number: Int, @Body params: ChallengeRequestData): Call<APIResponseData>

//    @POST("challenge/{challengeId}/validator")
//    fun addValidatorData(@Path("challengeId") number: Int, @Body params: ValidatorListData): Call<APIResponseData>

    @GET("challenge/{challengeId}")
    fun getChallengeData(@Path("challengeId") number: Int): Call<APIResponseData>

    @GET("challenge/{memberId}/list")
    fun getAllChallenges(@Path("memberId") number: Int): Call<APIResponseData>

    @PUT("challenge/{challengeId}")
    fun editChallengeData(@Path("challengeId") number: Int, @Body params: ChallengeRequestData): Call<APIResponseData>

    @DELETE("challenge/{challengeId}")
    fun deleteChallengeData(@Path("challengeId") number: Int): Call<APIResponseData>


    // Validation
    @POST("validation/Entry/{challengeId}/{memberId}")
    fun enterChallengeValidation(
        @Path("challengeId") cId: Int,
        @Path("memberId") mId: Int
    ): Call<APIResponseData>


    // Challenge Post
    @Multipart
    @POST("proof/{challengeId}") // 실제 API 엔드포인트를 여기에 입력
    fun uploadFile(
        @Path("challengeId") cId: Int,
        @PartMap data: HashMap<String, RequestBody>,
        @Part image: MultipartBody.Part?
    ): Call<APIResponseData>


}