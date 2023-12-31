package com.example.eom_fe.functions

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.eom_fe.activities.MainActivity
import com.example.eom_fe.api.RetrofitBuilder
import com.example.eom_fe.data.APIResponseData
import com.example.eom_fe.data.FirebaseToken
import com.example.eom_fe.data.MemberData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class LoginFunctions(context: Context, applicationContext: Context) {

    val mainContext = context
    val applicationContext = applicationContext

    var memberInfo: MemberData = MemberData(0, "", "", "", 0, 0, "")

    var memberToken: String = ""
    val dataFunctions = DataFunctions(mainContext, this.applicationContext)


    suspend fun kakaoLogin2(memberToken: String): MemberData {
        return suspendCancellableCoroutine { continuation ->
            // 로그인 조합 예제
            Log.e("eyesonme-LF", "카카오 로그인 호출")
            // 카카오계정으로 로그인 공통 callback 구성
            // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
            val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                Log.d("eyesonme-LF", "callback?")
                if (error != null) {
                    Log.e("eyesonme-LF", "카카오계정으로 로그인 실패", error)

                } else if (token != null) {
                    Log.i("eyesonme-LF", "카카오계정으로 로그인 성공 ${token.accessToken}")
                    UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
                        UserApiClient.instance.me { user, error ->
                            Log.d(
                                "eyesonme-LF",
                                "name : ${user?.kakaoAccount?.profile?.nickname}, 이메일 : ${user?.kakaoAccount?.email}"
                            )
                            val name: String = user?.kakaoAccount?.profile?.nickname.toString()
                            val email: String = user?.kakaoAccount?.email.toString()
                            val profileUrl: String =
                                user?.kakaoAccount?.profile?.profileImageUrl.toString()
                            findLoginInfoHttp(email) { memberInfo ->
                                if (memberInfo == null) {
                                    Log.d("eyesonme-LF", "memberInfo == null")
                                    val memberData =
                                        MemberData(0, name, email, profileUrl, 0, 0, memberToken)
                                    createMemberInfo(memberData) { createdMemberInfo ->
//                                    val i = Intent(this, AlarmListActivity::class.java)
//                                    startActivity(i)
                                        this.memberInfo = createdMemberInfo
                                        MainActivity.mInfo = createdMemberInfo
                                        continuation.resume(createdMemberInfo, null)
                                    }
                                } else {
//                                val i = Intent(this, AlarmListActivity::class.java)
//                                startActivity(i)
                                    this.memberInfo = memberInfo
                                    MainActivity.mInfo = memberInfo
                                    continuation.resume(memberInfo, null)
                                }

                            }
//                        finish()
//                                    nickname.text = "닉네임: ${user?.kakaoAccount?.profile?.nickname}"
                        }
                    }
                }
            }
            // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(mainContext)) {
                UserApiClient.instance.loginWithKakaoTalk(mainContext) { token, error ->
                    if (error != null) {
                        Log.e("eyesonme-LF", "카카오톡으로 로그인 실패", error)

                        // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                        // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }

                        // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                        UserApiClient.instance.loginWithKakaoAccount(
                            mainContext,
                            callback = callback
                        )
                    } else if (token != null) {
                        Log.i("eyesonme-LF", "카카오톡으로 로그인 성공 ${token.accessToken}")
                        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
                            UserApiClient.instance.me { user, error ->
                                Log.d(
                                    "eyesonme-LF",
                                    "name : ${user?.kakaoAccount?.profile?.nickname}, 이메일 : ${user?.kakaoAccount?.email}"
                                )
//                                    nickname.text = "닉네임: ${user?.kakaoAccount?.profile?.nickname}"
                            }
                        }
//                    finish()
                    }
                }
            } else {
                Log.d("eyesonme-LF", "이게무슨일이야")
                UserApiClient.instance.loginWithKakaoAccount(
                    context = mainContext,
                    callback = callback
                )
            }
        }
    }

    interface KakaoLoginCallback {
        fun onLoginComplete(memberInfo: MemberData)
        fun onLoginFailure(error: Throwable)
    }

    fun kakaoLogin(memberToken: String, callback: KakaoLoginCallback): MemberData {

        // 로그인 조합 예제
        Log.e("eyesonme-LF", "카카오 로그인 호출")
        // 카카오계정으로 로그인 공통 callback 구성
        // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            Log.d("eyesonme-LF", "callback?" )
            if (error != null) {
                Log.e("eyesonme-LF", "카카오계정으로 로그인 실패", error)

            } else if (token != null) {
                Log.i("eyesonme-LF", "카카오계정으로 로그인 성공 ${token.accessToken}")
                UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
                    UserApiClient.instance.me { user, error ->
                        Log.d(
                            "eyesonme-LF",
                            "name : ${user?.kakaoAccount?.profile?.nickname}, 이메일 : ${user?.kakaoAccount?.email}"
                        )
                        val name: String = user?.kakaoAccount?.profile?.nickname.toString()
                        val email: String = user?.kakaoAccount?.email.toString()
                        val profileUrl: String = user?.kakaoAccount?.profile?.profileImageUrl.toString()
                        findLoginInfoHttp(email) { memberInfo ->
                            if (memberInfo == null) {
                                Log.d("eyesonme-LF", "memberInfo == null")
                                val memberData = MemberData(0, name, email, profileUrl, 0, 0, memberToken)
                                createMemberInfo(memberData) { createdMemberInfo ->
                                    this.memberInfo = createdMemberInfo
                                    MainActivity.mInfo = createdMemberInfo
                                    dataFunctions.init(this.memberInfo)
                                    val firebaseTokenBuilder = RetrofitBuilder.api.renewFirebaseToken(this.memberInfo.id, FirebaseToken(memberToken))
                                    firebaseTokenBuilder.enqueue(object : Callback<APIResponseData> {
                                        override fun onResponse(
                                            call: Call<APIResponseData>,
                                            response: Response<APIResponseData>
                                        ) {
                                            Log.d("eyesonme-LF", "firebaseTokenBuilder onResponse")
                                            if (response.isSuccessful) {
                                                Log.d("eyesonme-LF", "firebaseTokenBuilder isSuccessful")

                                                Log.d("eyesonme-LF", "response : ${response.body()}")
                                                val temp = response.body() as APIResponseData
                                                val type: Type = object : TypeToken<Boolean>() {}.type
                                                val jsonResult = Gson().toJson(temp.data)
                                                val result = Gson().fromJson(jsonResult, type) as Boolean
                                                callback.onLoginComplete(createdMemberInfo)
                                            }
                                            else {
                                                Log.d("eyesonme-LF", "firebaseTokenBuilder !Successful")
                                                callback.onLoginComplete(createdMemberInfo)
                                            }
                                        }

                                        override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
                                            Log.d("eyesonme-LF", "firebaseTokenBuilder onFailure")
                                            callback.onLoginFailure(t)
                                        }
                                    }
                                    )
                                }
                            }
                            else {
                                this.memberInfo = memberInfo
                                MainActivity.mInfo = memberInfo
                                dataFunctions.init(this.memberInfo)
                                val firebaseTokenBuilder = RetrofitBuilder.api.renewFirebaseToken(memberInfo.id, FirebaseToken(memberToken))
                                firebaseTokenBuilder.enqueue(object : Callback<APIResponseData> {
                                    override fun onResponse(
                                        call: Call<APIResponseData>,
                                        response: Response<APIResponseData>
                                    ) {
                                        Log.d("eyesonme-LF", "firebaseTokenBuilder onResponse")
                                        if (response.isSuccessful) {
                                            Log.d("eyesonme-LF", "firebaseTokenBuilder isSuccessful")

                                            Log.d("eyesonme-LF", "response : ${response.body()}")
                                            val temp = response.body() as APIResponseData
                                            val type: Type = object : TypeToken<Boolean>() {}.type
                                            val jsonResult = Gson().toJson(temp.data)
                                            val result = Gson().fromJson(jsonResult, type) as Boolean
                                            callback.onLoginComplete(memberInfo)
                                        }
                                        else {
                                            Log.d("eyesonme-LF", "firebaseTokenBuilder !Successful")
                                            callback.onLoginComplete(memberInfo)
                                        }
                                    }

                                    override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
                                        Log.d("eyesonme-LF", "firebaseTokenBuilder onFailure")
                                        callback.onLoginFailure(t)
                                    }
                                }
                                )
                            }
                        }
                    }
                }
            }
        }
        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(mainContext)) {
            UserApiClient.instance.loginWithKakaoTalk(mainContext) { token, error ->
                if (error != null) {
                    Log.e("eyesonme-LF", "카카오톡으로 로그인 실패", error)

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(
                        mainContext,
                        callback = callback
                    )
                } else if (token != null) {
                    Log.i("eyesonme-LF", "카카오톡으로 로그인 성공 ${token.accessToken}")
                    UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
                        UserApiClient.instance.me { user, error ->
                            Log.d(
                                "eyesonme-LF",
                                "name : ${user?.kakaoAccount?.profile?.nickname}, 이메일 : ${user?.kakaoAccount?.email}"
                            )
                            val name: String = user?.kakaoAccount?.profile?.nickname.toString()
                            val email: String = user?.kakaoAccount?.email.toString()
                            val profileUrl: String = user?.kakaoAccount?.profile?.profileImageUrl.toString()
                            findLoginInfoHttp(email) { memberInfo ->
                                if (memberInfo == null) {
                                    Log.d("eyesonme-LF", "memberInfo == null")
                                    val memberData = MemberData(0, name, email, profileUrl, 0, 0, memberToken)
                                    createMemberInfo(memberData) { createdMemberInfo ->
                                        this.memberInfo = createdMemberInfo
                                        MainActivity.mInfo = createdMemberInfo
                                        dataFunctions.init(this.memberInfo)
                                        val firebaseTokenBuilder = RetrofitBuilder.api.renewFirebaseToken(this.memberInfo.id, FirebaseToken(memberToken))
                                        firebaseTokenBuilder.enqueue(object : Callback<APIResponseData> {
                                            override fun onResponse(
                                                call: Call<APIResponseData>,
                                                response: Response<APIResponseData>
                                            ) {
                                                Log.d("eyesonme-LF", "firebaseTokenBuilder onResponse")
                                                if (response.isSuccessful) {
                                                    Log.d("eyesonme-LF", "firebaseTokenBuilder isSuccessful")

                                                    Log.d("eyesonme-LF", "response : ${response.body()}")
                                                    val temp = response.body() as APIResponseData
                                                    val type: Type = object : TypeToken<Boolean>() {}.type
                                                    val jsonResult = Gson().toJson(temp.data)
                                                    val result = Gson().fromJson(jsonResult, type) as Boolean
                                                }
                                                else {
                                                    Log.d("eyesonme-LF", "firebaseTokenBuilder !Successful")

                                                }
                                            }

                                            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
                                                Log.d("eyesonme-LF", "firebaseTokenBuilder onFailure")
                                            }
                                        }
                                        )
                                    }
                                }
                                else {
                                    this.memberInfo = memberInfo
                                    MainActivity.mInfo = memberInfo
                                    dataFunctions.init(this.memberInfo)
                                    val firebaseTokenBuilder = RetrofitBuilder.api.renewFirebaseToken(memberInfo.id, FirebaseToken(memberToken))
                                    firebaseTokenBuilder.enqueue(object : Callback<APIResponseData> {
                                        override fun onResponse(
                                            call: Call<APIResponseData>,
                                            response: Response<APIResponseData>
                                        ) {
                                            Log.d("eyesonme-LF", "firebaseTokenBuilder onResponse")
                                            if (response.isSuccessful) {
                                                Log.d("eyesonme-LF", "firebaseTokenBuilder isSuccessful")

                                                Log.d("eyesonme-LF", "response : ${response.body()}")
                                                val temp = response.body() as APIResponseData
                                                val type: Type = object : TypeToken<Boolean>() {}.type
                                                val jsonResult = Gson().toJson(temp.data)
                                                val result = Gson().fromJson(jsonResult, type) as Boolean
                                            }
                                            else {
                                                Log.d("eyesonme-LF", "firebaseTokenBuilder !Successful")

                                            }
                                        }

                                        override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
                                            Log.d("eyesonme-LF", "firebaseTokenBuilder onFailure")

                                        }
                                    }
                                    )
                                }
                            }
                        }
                    }
//                    finish()
                }
            }
        } else {
            Log.d("eyesonme-LF", "이게무슨일이야")
            UserApiClient.instance.loginWithKakaoAccount(
                context = mainContext,
                callback = callback
            )
        }
        return memberInfo
    }

    private fun findLoginInfoHttp(email: String, callback: (memberInfo: MemberData?) -> Unit) {
        Log.d("eyesonme-LF", "findLoginHttp called")
        Log.d("eyesonme-LF", "email : $email")
        val findMemberInfo = RetrofitBuilder.api.findMemberData(email)
        findMemberInfo.enqueue(object : Callback<APIResponseData> {
            override fun onResponse(call: Call<APIResponseData>, response: Response<APIResponseData>) {
                Log.d("eyesonme-LF" ,response.errorBody().toString())
                Log.d("eyesonme-LF" ,response.code().toString())
                if (response.isSuccessful) {
                    // 사용자 정보 있음
                    Log.d("eyesonme-LF", "findLoginHttp -> response.isSuccessful")
                    //Toast.makeText(applicationContext, "Call Success", Toast.LENGTH_LONG).show()
                    Log.d("eyesonme-LF", "response.body : ${response.body()}")
//                    memberInfo = response.body()!!
                    val userApiResponseData = response.body() as APIResponseData
                    val type: Type = object : TypeToken<MemberData>() {}.type
                    val jsonResult = Gson().toJson(userApiResponseData.data)
                    memberInfo = Gson().fromJson(jsonResult, type) as MemberData
//                    memberInfo = userApiResponseData.data as MemberData
                    Log.d("eyesonme-LF", "memberInfo : ${memberInfo.toString()}")
                    callback(memberInfo)
                }
                else {
                    // 사용자 정보 수신 실패
                    Log.d("eyesonme-LF", "findLoginHttp -> !response.isSuccessful")
                    callback(null)
                }
            }

            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
                // 사용자 정보 없음
                callback(null)
                Log.d("eyesonme-LF", "findLoginHttp -> onFailure")
            }
        }
        )
    }

    private fun createMemberInfo(memberData: MemberData, callback: (createdMemberInfo: MemberData) -> Unit) {
        val createMemberInfo = RetrofitBuilder.api.addMemberData(memberData)
        Log.d("eyesonme-LF" ,"createMemberInfo called")
        Log.d("eyesonme-LF", "original data : ${memberData.toString()}")
        createMemberInfo.enqueue(object : Callback<APIResponseData> {
            override fun onResponse(call: Call<APIResponseData>, response: Response<APIResponseData>) {
                Log.d("eyesonme-LF" ,response.errorBody().toString())
                Log.d("eyesonme-LF" ,response.code().toString())
                Log.d("eyesonme-LF", response.raw().toString())
                if (response.isSuccessful) {
                    Log.d("eyesonme-LF", "createMemberInfo -> response.isSuccessful")
                    //Toast.makeText(applicationContext, "Call Success", Toast.LENGTH_LONG).show()
                    Log.d("eyesonme-LF", "response -> ${response}")
                    Log.d("eyesonme-LF", "response.body : ${response.body()}")
                    val userApiResponseData = response.body() as APIResponseData
                    val type: Type = object : TypeToken<MemberData>() {}.type
                    val jsonResult = Gson().toJson(userApiResponseData.data)
                    memberInfo = Gson().fromJson(jsonResult, type) as MemberData
//                    memberInfo = userApiResponseData.data as MemberData
//                    memberInfo = response.body()!!
                    Log.d("eyesonme-LF", "memberInfo : ${memberInfo.toString()}")
                    callback(memberInfo)
                } else {
                    // POST 요청 실패 처리
//                    callback(null)
                    Log.d("eyesonme-LF", "createMemberInfo -> !response.isSuccessful")
                }
            }

            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
                // POST 요청 실패 처리
//                callback(null)
                Log.d("eyesonme-LF", "createMemberInfo -> onFailure")
            }
        })
    }

}