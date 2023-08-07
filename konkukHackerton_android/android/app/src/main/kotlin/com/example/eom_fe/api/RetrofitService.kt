package com.example.eom_fe.api

import com.example.eom_fe.data.MemberData
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    //Member
    @POST("member")
    fun addMemberData(@Body params: MemberData): Call<MemberData>

    @GET("member/{memberId}")
    fun getMemberData(@Path("memberId") number: Int): Call<MemberData>

    @GET("member/{email}")
    fun findMemberData(@Path("email") email: String): Call<MemberData>

}