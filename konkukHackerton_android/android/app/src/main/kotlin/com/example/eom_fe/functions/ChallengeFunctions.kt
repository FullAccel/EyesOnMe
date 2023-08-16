package com.example.eom_fe.functions

import android.content.ActivityNotFoundException
import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import com.example.eom_fe.alarm_package.AlarmFunctions
import com.example.eom_fe.api.RetrofitBuilder
import com.example.eom_fe.data.*
import com.example.eom_fe.roomDB.AlarmDB
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.share.ShareClient
import com.kakao.sdk.share.WebSharerClient
import com.kakao.sdk.template.model.Button
import com.kakao.sdk.template.model.Link
import com.kakao.sdk.template.model.TextTemplate
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
    suspend fun getAllValidators(cId: Int): List<ValidatorListData>? {
        return try {
            val challengeData = withContext(Dispatchers.IO) {
                getChallengeDataFunc(cId)
            }
            challengeData?.validatorList
        } catch (e: Exception) {
            null
        }
    }

    /*
    // 실제 : 검증자 추가하기
    fun addValidator(cId: Int, vld: List<String>) {
        CoroutineScope(Dispatchers.IO).launch {
            val validatorList = ValidatorListData(vld)
            addValidatorDataFunc(cId, validatorList)

        }
    }

     */

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
//                CoroutineScope(Dispatchers.IO).launch {
//                    if (vld == null) {
//                        addValidatorDataFunc(challengeId, null)
//
//                    } else {
//                        val validatorList = ValidatorListData(vld)
//                        addValidatorDataFunc(challengeId, validatorList)
//                    }
//
//                }
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
    // 실제 사용 함수
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

/*
    fun addValidatorDataFunc(cId: Int, vld: ValidatorListData?) {
        if (vld != null) {
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
        else {
            Log.d("eyesonme-CF", "addValidatorDataFunc validatorList is null")
        }
    }

 */

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

    // 실제
    // date : "yyyy-mm-dd" 형태로
    fun postChallengeImage(cId: Int, date: String, writing: String, filePath: String, callback: (String?) -> Unit) {
        val date = date
        val writing = writing
        val dateRequestBody : RequestBody = date.toRequestBody()
        val writingRequestBody : RequestBody = writing.toRequestBody()
        val textHashMap = hashMapOf<String, RequestBody>()
        textHashMap["date"] = dateRequestBody
        textHashMap["writing"] = writingRequestBody

        Log.d("eyesonme-CF", "postChallengeImage called")

//        val file = File(mainContext.filesDir, fileName)
        val file = File(filePath) // 업로드할 파일 경로
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
                    callback(result.images.last().accessUrl)
                }
                else {
                    Log.d("eyesonme-CF", "postChallengeImage null (1)")
                    Log.d("eyesonme-CF", "error 1 : ${response.errorBody()!!.string()}")
                    callback(null)
                }
            }

            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
                Log.d("eyesonme-CF", "postChallengeImage null (2)")
                Log.d("eyesonme-CF", "error 2 : ${t.printStackTrace()}")
                callback(null)
            }
        }
        )
    }

    // 실제 : 검증자가 방에 입장
    fun enterValidation(cId: Int) {
        Log.d("eyesonme-CF", "enterValidation called")
        val enterValidationBuilder = RetrofitBuilder.api.enterChallengeValidation(cId, memberInfo.id)
        enterValidationBuilder.enqueue(object : Callback<APIResponseData> {
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
                    Log.d("eyesonme-CF", "enterValidation null (1)")
                    Log.d("eyesonme-CF", "error 1 : ${response.errorBody()!!.string()}")
                }
            }

            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
                Log.d("eyesonme-CF", "enterValidation null (2)")
                Log.d("eyesonme-CF", "error 2 : ${t.printStackTrace()}")
            }
        }
        )
    }

    // 실제 : 카카오톡 메시지 전송
    fun sendKakaoValidationMessage(cId: Int, title: String, validationIntervalCode: String, validationCountPerInterval: Int) {
        Log.d("eyesonme-CF", "sendKakaoValidationMessage called")
        val hostName = memberInfo.name
        var vic: String = ""
        val vcpi: Int = validationCountPerInterval
        vic = when (validationIntervalCode) {
            "VI01" -> " 하루에"
            "VI07" -> " 일주일에"
            "VI14" -> " 이주일에"
            "VI30" -> " 한 달에"
            else -> ""
        }
        val defaultText = TextTemplate(
            text = """
                지속 가능한 강력한 플래너 어플리케이션, “아이투두”에서 알려드립니다.\n
                ${hostName}님께서 당신을 아래 챌린지 검증 위원으로 설정하였습니다.\n
                “${title} :${vic} ${vcpi}번 하기!”\n
                ${hostName}님께서 빚어낼 변화의 첫 걸음 함께 응원해주시길 바랍니다!\n
                -${hostName}님의 성취를 열원하며, 아이투두 올림-
            """.trimIndent(),
            link = Link(
//                webUrl = "https://developers.kakao.com",
//                mobileWebUrl = "https://developers.kakao.com"
            ),
            buttons = listOf(
                Button(
                    "자세히 보기",
                    Link(
                        //이 부분을 사용해서 어떤 상세페이지를 띄울지 결정할수 있다 ⭐️⭐️⭐️⭐️⭐️
                        androidExecutionParams = mapOf(
                            "cIdString" to cId.toString()
                        )
                    ),
                )
            )
        )
        // 피드 메시지 보내기

        // 카카오톡 설치여부 확인
        if (ShareClient.instance.isKakaoTalkSharingAvailable(mainContext)) {
            // 카카오톡으로 카카오톡 공유 가능
            ShareClient.instance.shareDefault(mainContext, defaultText) { sharingResult, error ->
                if (error != null) {
                    Log.e("eyesonme-CF", "카카오톡 공유 실패", error)
                }
                else if (sharingResult != null) {
                    Log.d("eyesonme-CF", "카카오톡 공유 성공 ${sharingResult.intent}")
                    mainContext.startActivity(sharingResult.intent)

                    // 카카오톡 공유에 성공했지만 아래 경고 메시지가 존재할 경우 일부 컨텐츠가 정상 동작하지 않을 수 있습니다.
                    Log.w("eyesonme-CF", "Warning Msg: ${sharingResult.warningMsg}")
                    Log.w("eyesonme-CF", "Argument Msg: ${sharingResult.argumentMsg}")
                }
            }
        }
        else {
            // 카카오톡 미설치: 웹 공유 사용 권장
            // 웹 공유 예시 코드
            val sharerUrl = WebSharerClient.instance.makeDefaultUrl(defaultText)

            // CustomTabs으로 웹 브라우저 열기

            // 1. CustomTabsServiceConnection 지원 브라우저 열기
            // ex) Chrome, 삼성 인터넷, FireFox, 웨일 등
            try {
                KakaoCustomTabsClient.openWithDefault(mainContext, sharerUrl)
            } catch(e: UnsupportedOperationException) {
                // CustomTabsServiceConnection 지원 브라우저가 없을 때 예외처리
            }

            // 2. CustomTabsServiceConnection 미지원 브라우저 열기
            // ex) 다음, 네이버 등
            try {
                KakaoCustomTabsClient.open(mainContext, sharerUrl)
            } catch (e: ActivityNotFoundException) {
                // 디바이스에 설치된 인터넷 브라우저가 없을 때 예외처리
            }
        }
    }

    // 실제 : validator 지우기
    fun deleteValidatorDataFunc(cId: Int, vId: Int) {
        val deleteValidatorDataBuilder = RetrofitBuilder.api.deleteValidatorData(cId, vId)
        deleteValidatorDataBuilder.enqueue(object : Callback<APIResponseData> {
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
                    Log.d("eyesonme-CF", "deleteValidatorDataFunc null (1)")
                    Log.d("eyesonme-CF", "error 1 : ${response.errorBody()!!.string()}")
                }
            }

            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
                Log.d("eyesonme-CF", "deleteValidatorDataFunc null (2)")
                Log.d("eyesonme-CF", "error 2 : ${t.printStackTrace()}")
            }
        }
        )
    }

    // 실제 : 챌린지 검증 인정~
    fun checkValidationAsCompleteFunc(cId: Int) {
        val checkValidationAsCompleteBuilder = RetrofitBuilder.api.checkValidationAsComplete(cId, memberInfo.id)
        checkValidationAsCompleteBuilder.enqueue(object : Callback<APIResponseData> {
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
                    Log.d("eyesonme-CF", "checkValidationAsCompleteFunc null (1)")
                    Log.d("eyesonme-CF", "error 1 : ${response.errorBody()!!.string()}")
                }
            }
            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
                Log.d("eyesonme-CF", "checkValidationAsCompleteFunc null (2)")
                Log.d("eyesonme-CF", "error 2 : ${t.printStackTrace()}")
            }
        }
        )
    }

    // 실제 : 챌린지 검증 노인정~
    fun checkValidationAsFailFunc(cId: Int) {
        val checkValidationAsFailBuilder = RetrofitBuilder.api.checkValidationAsFail(cId, memberInfo.id)
        checkValidationAsFailBuilder.enqueue(object : Callback<APIResponseData> {
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
                    Log.d("eyesonme-CF", "checkValidationAsFailFunc null (1)")
                    Log.d("eyesonme-CF", "error 1 : ${response.errorBody()!!.string()}")
                }
            }
            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
                Log.d("eyesonme-CF", "checkValidationAsFailFunc null (2)")
                Log.d("eyesonme-CF", "error 2 : ${t.printStackTrace()}")
            }
        }
        )
    }

    // 실제 : 내가 validator인 챌린지 모두 가져오기
    fun getChallengeListFromValidatorFunc(callback: (List<ChallengeDataFromValidator>?) -> Unit) {
        val getChallengeListFromValidatorBuilder = RetrofitBuilder.api.getChallengeListFromValidator(memberInfo.id)
        getChallengeListFromValidatorBuilder.enqueue(object : Callback<APIResponseData> {
            override fun onResponse(
                call: Call<APIResponseData>,
                response: Response<APIResponseData>
            ) {
                if (response.isSuccessful) {
                    Log.d("eyesonme-CF", "response : ${response.body()}")
                    val temp = response.body() as APIResponseData
                    val type: Type = object : TypeToken<List<ChallengeDataFromValidator>>() {}.type
                    val jsonResult = Gson().toJson(temp.data)
                    val result = Gson().fromJson(jsonResult, type) as List<ChallengeDataFromValidator>
                    callback(result)
                }
                else {
                    Log.d("eyesonme-CF", "getChallengeListFromValidatorFunc null (1)")
                    Log.d("eyesonme-CF", "error 1 : ${response.errorBody()!!.string()}")
                    callback(null)
                }
            }
            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
                Log.d("eyesonme-CF", "getChallengeListFromValidatorFunc null (2)")
                Log.d("eyesonme-CF", "error 2 : ${t.printStackTrace()}")
                callback(null)
            }
        }
        )
    }

    // 실제 : 특정 proof 가져오기
    fun getProofDataFunc(cId: Int, date: DateDataForGettingProof, callback: (ProofResponseData?) -> Unit) {
        val getProofDataFuncBuilder = RetrofitBuilder.api.getProofData(cId, date)
        getProofDataFuncBuilder.enqueue(object : Callback<APIResponseData> {
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
                    callback(result)
                }
                else {
                    Log.d("eyesonme-CF", "getProofDataFunc null (1)")
                    Log.d("eyesonme-CF", "error 1 : ${response.errorBody()!!.string()}")
                    callback(null)
                }
            }
            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
                Log.d("eyesonme-CF", "getProofDataFunc null (2)")
                Log.d("eyesonme-CF", "error 2 : ${t.printStackTrace()}")
                callback(null)
            }
        }
        )
    }

    // 실제 : 모든 proof 리스트로 가져오기
    fun getAllProofDataFunc(cId: Int, callback: (List<ProofResponseData>?) -> Unit) {
        val getAllProofDataFuncBuilder = RetrofitBuilder.api.getAllProofData(cId)
        getAllProofDataFuncBuilder.enqueue(object : Callback<APIResponseData> {
            override fun onResponse(
                call: Call<APIResponseData>,
                response: Response<APIResponseData>
            ) {
                if (response.isSuccessful) {
                    Log.d("eyesonme-CF", "response : ${response.body()}")
                    val temp = response.body() as APIResponseData
                    val type: Type = object : TypeToken<List<ProofResponseData>>() {}.type
                    val jsonResult = Gson().toJson(temp.data)
                    val result = Gson().fromJson(jsonResult, type) as List<ProofResponseData>
                    callback(result)
                }
                else {
                    Log.d("eyesonme-CF", "getAllProofDataFunc null (1)")
                    Log.d("eyesonme-CF", "error 1 : ${response.errorBody()!!.string()}")
                    callback(null)
                }
            }
            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
                Log.d("eyesonme-CF", "getAllProofDataFunc null (2)")
                Log.d("eyesonme-CF", "error 2 : ${t.printStackTrace()}")
                callback(null)
            }
        }
        )
    }



}