package com.example.eom_fe.functions

import android.content.Context
import android.util.Log
import com.example.eom_fe.activities.MainActivity
import com.example.eom_fe.alarm_package.AlarmFunctions
import com.example.eom_fe.api.RetrofitBuilder
import com.example.eom_fe.data.*
import com.example.eom_fe.roomDB.AlarmDB
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.flutter.plugin.common.EventChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class ChallengeFunctions (context: Context, applicationContext: Context) {

    val mainContext = context
    val applicationContext = applicationContext

    var memberInfo: MemberData = MainActivity.mInfo!!

    private lateinit var eventChannel: EventChannel
    val alarmFunctions = AlarmFunctions(context)
    val db = AlarmDB.getDatabase(applicationContext)

//    fun init(memberData: MemberData) {
////        memberInfo initial 필요
//        memberInfo = memberData
//        Log.d("eyesonme-CF", "init: $memberInfo")
//    }
//
//    fun init2() {
//        memberInfo = MainActivity.mInfo!!
//    }

    // 실제 : 모든 챌린지 보기
    fun getAllChallenges() {

    }

    // 실제 : 모든 인증 친구 가져오기
    suspend fun getAllValidators(cId: Int): List<String>? {
        return try {
            val challengeData = withContext(Dispatchers.IO) {
                getChallengeDataFunc(cId)
            }
            challengeData?.validatorList
        } catch (e: Exception) {
            null
        }
    }


    // 실제 : 챌린지 하나의 정보 가져오기
    fun getSingleChallenge() {

    }

    // 실제 : 검증자 추가하기
    fun addValidatior() {

    }

    // 실제 : 재검증 요청하기
    fun requestVerifyAgain() {

    }

    // 실제 : 특정 날짜에, 사진, 글 업로드
    fun uploadChallengePost() {

    }

    // 실제 : 검증 버튼 (미달성, 보류, 달성)
    fun requestValidation() {

    }

    // 실제 : 특정 챌린지에 개선점 POST (줄글만)
    fun uploadImprovement() {

    }


    // from RetrofitService
    fun addChallengeDataFunc(cd: ChallengeRequestData) {
        val addChallengeDataBuilder = RetrofitBuilder.api.addChallengeData(memberInfo.id, cd)
        addChallengeDataBuilder.enqueue(object : Callback<APIResponseData> {
            override fun onResponse(
                call: Call<APIResponseData>,
                response: Response<APIResponseData>
            ) {
                if (response.isSuccessful) {
                    Log.d("eyesonme-CF", "response : ${response.body()}")
                    val temp = response.body() as APIResponseData
                    val type: Type = object : TypeToken<Int>() {}.type
                    val jsonResult = Gson().toJson(temp.data)
                    val result = Gson().fromJson(jsonResult, type) as Int
                    // result == challengeId
                }
            }

            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
            }
        }
        )
    }

    fun addValidatorDataFunc(vld: ValidatorListData) {
        val addValidatorDataBuilder = RetrofitBuilder.api.addValidatorData(memberInfo.id, vld)
        addValidatorDataBuilder.enqueue(object : Callback<APIResponseData> {
            override fun onResponse(
                call: Call<APIResponseData>,
                response: Response<APIResponseData>
            ) {
                if (response.isSuccessful) {
                    Log.d("eyesonme-CF", "response : ${response.body()}")
                    val temp = response.body() as APIResponseData
                    val type: Type = object : TypeToken<Boolean>() {}.type
                    val jsonResult = Gson().toJson(temp.data)
                    val result = Gson().fromJson(jsonResult, type) as Boolean
                    // whether request success or not
                }
            }

            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
            }
        }
        )
    }

    fun getChallengeDataFunc2(cId: Int) {
        val getChallengeDataBuilder = RetrofitBuilder.api.getChallengeData(cId)
        getChallengeDataBuilder.enqueue(object : Callback<APIResponseData> {
            override fun onResponse(
                call: Call<APIResponseData>,
                response: Response<APIResponseData>
            ) {
                if (response.isSuccessful) {
                    Log.d("eyesonme-CF", "response : ${response.body()}")
                    val temp = response.body() as APIResponseData
                    val type: Type = object : TypeToken<ChallengeData>() {}.type
                    val jsonResult = Gson().toJson(temp.data)
                    val result = Gson().fromJson(jsonResult, type) as ChallengeData
                }
            }

            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
            }
        }
        )
    }

    suspend fun getChallengeDataFunc(cId: Int): ChallengeData? {
        return try {
            val getChallengeDataBuilder = RetrofitBuilder.api.getChallengeData(cId)
            val response = getChallengeDataBuilder.execute()

            if (response.isSuccessful) {
                Log.d("eyesonme-CF", "response : ${response.body()}")
                val temp = response.body() as APIResponseData
                val type: Type = object : TypeToken<ChallengeData>() {}.type
                val jsonResult = Gson().toJson(temp.data)
                val result = Gson().fromJson(jsonResult, type) as ChallengeData
                result
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getAllChallengesFunc(): List<ChallengeData>? {
        return try {
            val getAllChallengesBuilder = RetrofitBuilder.api.getAllChallenges(memberInfo.id)
            val response = getAllChallengesBuilder.execute()

            if (response.isSuccessful) {
                val temp = response.body() as APIResponseData
                val type: Type = object : TypeToken<List<ChallengeData>>() {}.type
                val jsonResult = Gson().toJson(temp.data)
                val result = Gson().fromJson(jsonResult, type) as List<ChallengeData>
                result
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    fun editChallengeDataFunc(cId: Int) {
        val editChallengeDataBuilder = RetrofitBuilder.api.editChallengeData(cId)
        editChallengeDataBuilder.enqueue(object : Callback<APIResponseData> {
            override fun onResponse(
                call: Call<APIResponseData>,
                response: Response<APIResponseData>
            ) {
                if (response.isSuccessful) {
                    Log.d("eyesonme-CF", "response : ${response.body()}")
                    val temp = response.body() as APIResponseData
                    val type: Type = object : TypeToken<Boolean>() {}.type
                    val jsonResult = Gson().toJson(temp.data)
                    val result = Gson().fromJson(jsonResult, type) as Boolean
                }
            }

            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
            }
        }
        )
    }

    fun deleteChallengeDataFunc(cId: Int) {
        val deleteChallengeDataBuilder = RetrofitBuilder.api.deleteChallengeData(cId)
        deleteChallengeDataBuilder.enqueue(object : Callback<APIResponseData> {
            override fun onResponse(
                call: Call<APIResponseData>,
                response: Response<APIResponseData>
            ) {
                if (response.isSuccessful) {
                    Log.d("eyesonme-CF", "response : ${response.body()}")
                    val temp = response.body() as APIResponseData
                    val type: Type = object : TypeToken<Boolean>() {}.type
                    val jsonResult = Gson().toJson(temp.data)
                    val result = Gson().fromJson(jsonResult, type) as Boolean
                }
            }

            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
            }
        }
        )
    }



}