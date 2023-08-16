package com.example.fluttertest.activities

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.fluttertest.R
import com.example.fluttertest.api.RetrofitBuilder
import com.example.fluttertest.alarm_package.AlarmFunctions
import com.example.fluttertest.roomDB.AlarmDB
import com.example.fluttertest.roomDB.AlarmDataModel
import com.example.fluttertest.data.MemberData
import com.example.fluttertest.data.UserApiResponseData
import com.example.fluttertest.databinding.ActivityMainBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val coroutineScope by lazy { CoroutineScope(Dispatchers.IO) }

    var data:ArrayList<AlarmDataModel> = ArrayList()
    lateinit var db: AlarmDB

    lateinit var memberInfo: MemberData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = AlarmDB.getDatabase(this)
        KakaoSdk.init(this, getString(R.string.kakao_hash_key))

        init()
    }

    val alarmFunctions = AlarmFunctions(this)

    private fun kakaoLogin() {

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
                        Log.d("welcome", "memberInfo -> $name, $email, $profileUrl")
                        findLoginInfoHttp(email) { memberInfo ->
                            if (memberInfo == null) {
                                Log.d("welcome", "memberInfo == null")
                                val memberData = MemberData(0, name, email, profileUrl, 0, 0)
                                createMemberInfo(memberData) { createdMemberInfo ->
                                    val i = Intent(this, AlarmListActivity::class.java)
                                    startActivity(i)
                                }
                            }
                            else {
                                val i = Intent(this, AlarmListActivity::class.java)
                                startActivity(i)
                            }

                        }
//                        finish()
//                                    nickname.text = "닉네임: ${user?.kakaoAccount?.profile?.nickname}"
                    }
                }
            }
        }
        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                if (error != null) {
                    Log.e("welcome", "카카오톡으로 로그인 실패", error)

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(
                        this,
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
                context = this@MainActivity,
                callback = callback
            )
        }
    }

    private fun findLoginInfoHttp(email: String, callback: (memberInfo: MemberData?) -> Unit){
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

    fun init() {

        binding.addAlarmBtn.setOnClickListener {
            val hour = binding.timePicker.hour.toString()
            val minute = binding.timePicker.minute.toString()
            val time = "2023-08-08 $hour:$minute:00" // 알람이 울리는 시간

            val random = (1..100000) // 1~100000 범위에서 알람코드 랜덤으로 생성
            val alarmCode = random.random()
            val content = binding.alarmText.text.toString()
            setAlarm(alarmCode, content, time)

            coroutineScope.launch(Dispatchers.IO) {
                db.alarmDao().addAlarm(AlarmDataModel(alarmCode, alarmCode, time, content))
                data = db.alarmDao().getAllAlarms() as ArrayList<AlarmDataModel>
                Log.d("alarmNotification", "getAllAlarm (MainActivity) - ${data.size}")
                launch(Dispatchers.Main) {
                    Log.d("alarmNotification", "getAllAlarm - launch executed")

                }
            }
        }

        binding.alarmListCheckBtn.setOnClickListener {
            val i = Intent(this, AlarmListActivity::class.java)
            startActivity(i)
        }
        binding.kakaoLoginBtn.setOnClickListener {
            kakaoLogin()
        }
    }

    private fun setAlarm(alarmCode : Int, content : String, time : String){
        alarmFunctions.callAlarm(time, alarmCode, content)
    }


    //권한 확인
    fun checkPermission() {

        // 1. 위험권한(Camera) 권한 승인상태 가져오기
        val cameraPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.SYSTEM_ALERT_WINDOW)
        if (cameraPermission == PackageManager.PERMISSION_GRANTED) {
            // 카메라 권한이 승인된 상태일 경우
            startProcess()

        } else {
            // 카메라 권한이 승인되지 않았을 경우
            requestPermission()
        }
    }

    // 2. 권한 요청
    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SYSTEM_ALERT_WINDOW), 99)
    }

    // 권한 처리
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            99 -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startProcess()
                } else {
                    Log.d("MainActivity", "종료")
                }
            }
        }
    }

    // 3. 카메라 기능 실행
    fun startProcess() {
        Toast.makeText(this, "카메라 기능 실행", Toast.LENGTH_SHORT).show()
    }

}