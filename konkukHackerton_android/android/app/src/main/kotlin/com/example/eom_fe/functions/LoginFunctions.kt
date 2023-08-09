package com.example.eom_fe.functions

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.*
import com.example.eom_fe.api.RetrofitBuilder
import com.example.eom_fe.data.MemberData
import com.example.eom_fe.data.UserApiResponseData
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Member

class LoginFunctions(context: Context, applicationContext: Context) {

    val mainContext = context
    val applicationContext = applicationContext

    var memberInfo: MemberData = MemberData(0, "", "", "", 0, 0)

    fun kakaoLogin(): MemberData {

        // 로그인 조합 예제
        Log.e("welcome", "카카오 로그인 호출")
        // 카카오계정으로 로그인 공통 callback 구성
        // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            Log.d("welcome", "callback?" )
            if (error != null) {
                Log.e("welcome", "카카오계정으로 로그인 실패", error)

            } else if (token != null) {
                Log.i("welcome", "카카오계정으로 로그인 성공 ${token.accessToken}")
                UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
                    UserApiClient.instance.me { user, error ->
                        Log.d(
                            "welcome",
                            "name : ${user?.kakaoAccount?.profile?.nickname}, 이메일 : ${user?.kakaoAccount?.email}"
                        )
                        val name: String = user?.kakaoAccount?.profile?.nickname.toString()
                        val email: String = user?.kakaoAccount?.email.toString()
                        val profileUrl: String = user?.kakaoAccount?.profile?.profileImageUrl.toString()
                        findLoginInfoHttp(email) { memberInfo ->
                            if (memberInfo == null) {
                                Log.d("welcome", "memberInfo == null")
                                val memberData = MemberData(0, name, email, profileUrl, 0, 0)
                                createMemberInfo(memberData) { createdMemberInfo ->
//                                    val i = Intent(this, AlarmListActivity::class.java)
//                                    startActivity(i)
                                }
                            }
                            else {
//                                val i = Intent(this, AlarmListActivity::class.java)
//                                startActivity(i)
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
                    Log.e("welcome", "카카오톡으로 로그인 실패", error)

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
                    Log.i("welcome", "카카오톡으로 로그인 성공 ${token.accessToken}")
                    UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
                        UserApiClient.instance.me { user, error ->
                            Log.d(
                                "welcome",
                                "name : ${user?.kakaoAccount?.profile?.nickname}, 이메일 : ${user?.kakaoAccount?.email}"
                            )
//                                    nickname.text = "닉네임: ${user?.kakaoAccount?.profile?.nickname}"
                        }
                    }
//                    finish()
                }
            }
        } else {
            Log.d("welcome", "이게무슨일이야")
            UserApiClient.instance.loginWithKakaoAccount(
                context = mainContext,
                callback = callback
            )
        }
        return memberInfo
    }

    private fun findLoginInfoHttp(email: String, callback: (memberInfo: MemberData?) -> Unit) {
        Log.d("welcome", "findLoginHttp called")
        Log.d("welcome", "email : $email")
        val findMemberInfo = RetrofitBuilder.api.findMemberData(email)
        findMemberInfo.enqueue(object : Callback<UserApiResponseData> {
            override fun onResponse(call: Call<UserApiResponseData>, response: Response<UserApiResponseData>) {
                Log.d("welcome" ,response.errorBody().toString())
                Log.d("welcome" ,response.code().toString())
                if (response.isSuccessful) {
                    // 사용자 정보 있음
                    Log.d("welcome", "findLoginHttp -> response.isSuccessful")
                    Toast.makeText(applicationContext, "Call Success", Toast.LENGTH_LONG).show()
                    Log.d("welcome", "response.body : ${response.body()}")
//                    memberInfo = response.body()!!
                    val userApiResponseData = response.body() as UserApiResponseData
                    memberInfo = userApiResponseData.data
                    Log.d("welcome", "memberInfo : ${memberInfo.toString()}")
                    callback(memberInfo)
                }
                else {
                    // 사용자 정보 수신 실패
                    Log.d("welcome", "findLoginHttp -> !response.isSuccessful")
                    callback(null)
                }
            }

            override fun onFailure(call: Call<UserApiResponseData>, t: Throwable) {
                // 사용자 정보 없음
                callback(null)
                Log.d("welcome", "findLoginHttp -> onFailure")
            }
        }
        )
    }

    private fun createMemberInfo(memberData: MemberData, callback: (createdMemberInfo: MemberData) -> Unit) {
        val createMemberInfo = RetrofitBuilder.api.addMemberData(memberData)
        Log.d("welcome" ,"createMemberInfo called")
        Log.d("welcome", "original data : ${memberData.toString()}")
        createMemberInfo.enqueue(object : Callback<UserApiResponseData> {
            override fun onResponse(call: Call<UserApiResponseData>, response: Response<UserApiResponseData>) {
                Log.d("welcome" ,response.errorBody().toString())
                Log.d("welcome" ,response.code().toString())
                Log.d("welcome", response.raw().toString())
                if (response.isSuccessful) {
                    Log.d("welcome", "createMemberInfo -> response.isSuccessful")
                    Toast.makeText(applicationContext, "Call Success", Toast.LENGTH_LONG).show()
                    Log.d("welcome", "response -> ${response}")
                    Log.d("welcome", "response.body : ${response.body()}")
                    val userApiResponseData = response.body() as UserApiResponseData
                    memberInfo = userApiResponseData.data
//                    memberInfo = response.body()!!
                    Log.d("welcome", "memberInfo : ${memberInfo.toString()}")
                    callback(memberInfo)
                } else {
                    // POST 요청 실패 처리
//                    callback(null)
                    Log.d("welcome", "createMemberInfo -> !response.isSuccessful")
                }
            }

            override fun onFailure(call: Call<UserApiResponseData>, t: Throwable) {
                // POST 요청 실패 처리
//                callback(null)
                Log.d("welcome", "createMemberInfo -> onFailure")
            }
        })
    }

}