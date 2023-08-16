package com.example.eom_fe.functions

import android.content.Context
import android.util.Log
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
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.lang.reflect.Type

class ChallengeFunctions (context: Context, applicationContext: Context) {

    val mainContext = context
    val applicationContext = applicationContext

//    var memberInfo: MemberData = MainActivity.mInfo!!

    var memberInfo: MemberData = MemberData(0, "", "", "", 0, 0, "")

    private lateinit var eventChannel: EventChannel
    val alarmFunctions = AlarmFunctions(context)
    val db = AlarmDB.getDatabase(applicationContext)

    fun init(memberData: MemberData) {
//        memberInfo initial 필요
        memberInfo = memberData
        Log.d("eyesonme-CF", "init: $memberInfo")
    }

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

    // 실제 : 검증자 추가하기
    fun addValidator(cId: Int, vld: List<String>) {
        CoroutineScope(Dispatchers.IO).launch {
            val validatorList = ValidatorListData(vld)
            addValidatorDataFunc(cId, validatorList)
        }
    }

    // 실제 : 특정 날짜에, 사진, 글 업로드
    fun uploadChallengePost() {

    }

    // 실제 : 검증 버튼 (미달성, 달성)
    fun requestValidation() {

    }

    // 실제 : 챌린지 만들기 (검증자와 함께)
    fun makeChallengeWithValidator(cd: ChallengeRequestData, vld: List<String>?) {
        addChallengeDataFunc(cd) { challengeId ->
            if (challengeId != null) {
                // 결과 값인 challengeId를 사용하여 다른 작업 수행
                CoroutineScope(Dispatchers.IO).launch {
                    if (vld == null) {
                        addValidatorDataFunc(challengeId, null)

                    } else {
                        val validatorList = ValidatorListData(vld)
                        addValidatorDataFunc(challengeId, validatorList)
                    }

                }
            } else {
                // 실패한 경우 처리
            }
        }
    }

    // 실제 : 챌린지 수정하기
    fun editChallenge(cId: Int, cd: ChallengeRequestData) {


    }


//    - [ ]  챌린지 수정하기


    // from RetrofitService
    fun addChallengeDataFunc(cd: ChallengeRequestData, callback: (Int?) -> Unit) {
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
                    callback(result) // 결과 값을 콜백 함수로 전달
                }
                else {
                    Log.d("eyesonme-CF", "addChallengeDataFunc null (1)")
                    Log.d("eyesonme-CF", "error 1 : ${response.errorBody()!!.string()}")
                    callback(null)
                }

            }
            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
                Log.d("eyesonme-CF", "addChallengeDataFunc null (2)")
                Log.d("eyesonme-CF", "error 2 : ${t.printStackTrace()}")
                callback(null) // 실패 시에도 콜백 호출, 결과 값은 null
            }
        })
    }


    fun addValidatorDataFunc(cId: Int, vld: ValidatorListData?) {
        val addValidatorDataBuilder = RetrofitBuilder.api.addValidatorData(cId, vld)
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
                else {
                    Log.d("eyesonme-CF", "addValidatorDataFunc null (1)")
                    Log.d("eyesonme-CF", "error 1 : ${response.errorBody()!!.string()}")

                }
            }

            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
                Log.d("eyesonme-CF", "addValidatorDataFunc null (2)")
                Log.d("eyesonme-CF", "error 2 : ${t.printStackTrace()}")
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

    // 실제 : 챌린지 하나의 정보 가져오기
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
                Log.d("eyesonme-CF", "getChallengeDataFunc null (1)")
                Log.d("eyesonme-CF", "error 1 : ${response.errorBody()!!.string()}")
                null
            }
        } catch (e: Exception) {
            Log.d("eyesonme-CF", "getChallengeDataFunc null (2)")
            Log.d("eyesonme-CF", "error 1 : ${e.printStackTrace()}")
            null
        }
    }

    fun getAllChallengesFunc(callback: (List<ChallengeData>?) -> Unit) {
        val getAllChallengesBuilder = RetrofitBuilder.api.getAllChallenges(memberInfo.id)
        getAllChallengesBuilder.enqueue(object : Callback<APIResponseData> {
            override fun onResponse(
                call: Call<APIResponseData>,
                response: Response<APIResponseData>
            ) {
                if (response.isSuccessful) {
                    Log.d("eyesonme-DF", "response : ${response.body()}")
                    val temp = response.body() as APIResponseData
                    val type: Type = object : TypeToken<List<ChallengeData>>() {}.type
                    val jsonResult = Gson().toJson(temp.data)
                    val result = Gson().fromJson(jsonResult, type) as List<ChallengeData>
                    callback(result)
                }
                else {
                    Log.d("eyesonme-DF", "getMonthlyPlanFunc null (1)")
                    Log.d("eyesonme-DF", "error 1 : ${response.errorBody()!!.string()}")
                    callback(null)
                }

            }

            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
                Log.d("eyesonme-DF", "getMonthlyPlanFunc null (2)")
                Log.d("eyesonme-DF", "error 2 : ${t.printStackTrace()}")
                callback(null)
            }
        }
        )
    }

//    suspend fun getAllChallengesFunc(): List<ChallengeData>? {
//        return try {
//            val getAllChallengesBuilder = RetrofitBuilder.api.getAllChallenges(memberInfo.id)
//            val response = getAllChallengesBuilder.execute()
//
//            if (response.isSuccessful) {
//                CoroutineScope(Dispatchers.IO).launch {
//                    Log.d("eyesonme-CF", "response : ${response.body()}")
//                    val temp = response.body() as APIResponseData
//                    val type: Type = object : TypeToken<List<ChallengeData>>() {}.type
//                    val jsonResult = Gson().toJson(temp.data)
//                    val result = Gson().fromJson(jsonResult, type) as List<ChallengeData>
//                    Log.d("eyesonme-CF", "responseSuccessful $result")
//                    result
//                }
//
//            } else {
//                Log.d("eyesonme-CF", "getAllChallengesFunc null (1)")
//                Log.d("eyesonme-CF", "error 1 : ${response.errorBody()!!.string()}")
//                null
//            }
//        } catch (e: Exception) {
//            Log.d("eyesonme-CF", "getAllChallengesFunc null (2)")
//            Log.d("eyesonme-CF", "error 2 : ${e.printStackTrace()}")
//            null
//        }
//    }

    // 실제 : 챌린지 수정 함수
    fun editChallengeDataFunc(cId: Int, cd: ChallengeRequestData) {
        val editChallengeDataBuilder = RetrofitBuilder.api.editChallengeData(cId, cd)
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
                else {
                    Log.d("eyesonme-CF", "editChallengeDataFunc null (1)")
                    Log.d("eyesonme-CF", "error 1 : ${response.errorBody()!!.string()}")
                }
            }

            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
                Log.d("eyesonme-CF", "editChallengeDataFunc null (2)")
                Log.d("eyesonme-CF", "error 2 : ${t.printStackTrace()}")
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

    fun postChallengeImage(cId: Int, fileName: String) {
        val date = "2023-08-16"
        val writing = "test :: disney plus moving"
        val dateRequestBody : RequestBody = date.toRequestBody()
        val writingRequestBody : RequestBody = writing.toRequestBody()
        val textHashMap = hashMapOf<String, RequestBody>()
        textHashMap["date"] = dateRequestBody
        textHashMap["writing"] = writingRequestBody

        val file = File(mainContext.filesDir, fileName)
//        val file = File(path) // 업로드할 파일 경로
        val fileRequestBody = file.asRequestBody("image/jpg".toMediaType()) // 파일의 MIME 타입 설정
        val filePart = MultipartBody.Part.createFormData("images", file.name, fileRequestBody)

        val postChallengeImageBuilder = RetrofitBuilder.api.uploadFile(cId, textHashMap, filePart)
        postChallengeImageBuilder.enqueue(object : Callback<APIResponseData> {
            override fun onResponse(
                call: Call<APIResponseData>,
                response: Response<APIResponseData>
            ) {
                if (response.isSuccessful) {
                    Log.d("eyesonme-CF", "response : ${response.body()}")
                    val temp = response.body() as APIResponseData
                    val type: Type = object : TypeToken<ProofResponseData>() {}.type
                    val jsonResult = Gson().toJson(temp.data)
                    val result = Gson().fromJson(jsonResult, type) as ProofResponseData
                    Log.d("eyesonme-CF", "result : $result")
                }
                else {
                    Log.d("eyesonme-CF", "postChallengeImage null (1)")
                    Log.d("eyesonme-CF", "error 1 : ${response.errorBody()!!.string()}")
                    Log.d("eyesonme-CF", "error 1 : ${response.raw()}")
                }
            }

            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
                Log.d("eyesonme-CF", "postChallengeImage null (2)")
                Log.d("eyesonme-CF", "error 2 : ${t.printStackTrace()}")
            }
        }
        )
    }

    fun sendValidatorKakaoMessage(cId: Int) {
        val sendValidatorKakaoMessageBuilder = RetrofitBuilder.api.enterChallengeValidation(cId, memberInfo.id)
        sendValidatorKakaoMessageBuilder.enqueue(object : Callback<APIResponseData> {
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
                else {
                    Log.d("eyesonme-CF", "sendValidatorKakaoMessage null (1)")
                    Log.d("eyesonme-CF", "error 1 : ${response.errorBody()!!.string()}")
                }
            }

            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
                Log.d("eyesonme-CF", "sendValidatorKakaoMessage null (2)")
                Log.d("eyesonme-CF", "error 2 : ${t.printStackTrace()}")
            }
        }
        )
    }



}