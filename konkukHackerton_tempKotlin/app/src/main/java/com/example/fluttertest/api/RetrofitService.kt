package com.example.fluttertest.api

import com.example.fluttertest.data.MemberData
import com.example.fluttertest.data.UserApiResponseData
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    //Member
    @POST("member")
    fun addMemberData(@Body params: MemberData): Call<UserApiResponseData>

    @GET("member/{memberId}")
    fun getMemberData(@Path("memberId") number: Int): Call<MemberData>

    @GET("member/follow/{email}")
    fun findMemberData(@Path("email") email: String): Call<UserApiResponseData>


    // Follow
    // Call<Any> 수정할 것
    @POST("follow/{memberId}/{followMemberId}")
    fun followOtherMember(
        @Path("memberId") number: Int,
        @Path("followMemberId") otherNumber: Int
    ): Call<Any>

//    @GET("follow/{followings}")
//    fun getFollowingList(@Path("followings" ))

    /*
    //Region


    //WishList
    @GET("wishList/recentHome/{memberId}")
    fun getRecentHomeList(@Path("memberId") number: Int): Call<WishListInfoData>

    @GET("wishList/zzimHome/{memberId}")
    fun getZzimHomeList(@Path("memberId") number: Int): Call<WishListInfoData>

    @POST("wishList/recentHome")
    fun addRecentHome()

    @POST("wishList/zzimHome/memberId")
    fun addZzimHome()

    //RealEstateDetail
    @GET("RealEstateDetail/{RealEstate_id}")
    fun getRealEstateDetail(@Path("RealEstate_id") number: Int): Call<RealEstateDetailData>


    //RealEstate
    @GET("RealEstate/{RealEstate_id}")
    fun getRealEstate(@Path("RealEstate_id") number: Int): Call<RealEstateData>


    //SearchPreset
    @GET("preset/find/{member_id}")
    fun getPresetList(@Path("member_id") number: Int): Call<PresetInfoData>

    @GET("preset/find/{member_id}/{preset_id}")
    fun getPreset(
        @Path("member_id") number: Int,
        @Path("preset_id") presetId: Int
    ): Call<PresetInfoData>

    @POST("preset/alter/{member_id}")
    fun addPreset(@Path("member_id") memId: Int, @Body params: PresetInfoData): Call<PresetInfoData>

    @DELETE("preset/alter/{member_id}/{preset_id}")
    fun deletePreset()


    //Map
    @GET("realEstate/bbox")
    fun getRealEstateInMap(): Call<RealEstateData>

    @GET("realEstate/bbox")
    fun getRealEstateInMapWithNoOption(
        @Query("location") location: String,
        @Query("filter") filter: String?
    ): Call<ArrayList<RealEstateData>>

    @GET("realEstate/bbox?location={LBLatitude}_{LBLongitude}_{RTLatitude}_{RTLongitude}&filter=null")
    fun getRealEstateInCluster()
    */


    // Example
    /*@POST("user/register")
    fun postUser(@Body params: HashMap<String, String>): Call<UserInfo>

    @GET("user/initNang/{userNo}")
    fun getNang(@Path("userNo") number: Int): Call<ResponseBody>

    @GET("dialog/open/{userNo}")
    fun getOpening(@Path("userNo") number: Int): Call<ResponseBody>

    @GET("dialog/isCreate/{userNo}")
    fun getOK(@Path("userNo") number: Int): Call<ResponseBody>*/
}