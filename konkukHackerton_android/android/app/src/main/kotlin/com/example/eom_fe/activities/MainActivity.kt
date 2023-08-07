package com.example.eom_fe.activities

import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

import com.example.eom_fe.R
import com.example.eom_fe.alarm_package.AlarmFunctions
import com.example.eom_fe.data.MemberData
import com.example.eom_fe.functions.LoginFunctions
import com.example.eom_fe.roomDB.AlarmDB
import com.example.eom_fe.roomDB.AlarmDataModel
import com.kakao.sdk.common.KakaoSdk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MainActivity: FlutterActivity() {
    private val CHANNEL = "samples.flutter.dev/battery"

//    lateinit var binding: ActivityMainBinding
    private val coroutineScope by lazy { CoroutineScope(Dispatchers.IO) }

    var data:ArrayList<AlarmDataModel> = ArrayList()
    lateinit var db: AlarmDB
    val alarmFunctions = AlarmFunctions(this)

    lateinit var memberInfo: MemberData
    lateinit var loginFunctions: LoginFunctions

    override fun onStart() {
        super.onStart()

        db = AlarmDB.getDatabase(this)
        KakaoSdk.init(this, getString(R.string.kakao_hash_key))
        loginFunctions = LoginFunctions(this, applicationContext)
        initLogin()
    }

    private fun initLogin() {
        loginFunctions.kakaoLogin()
        // kakaoLogin이 끝난 후 getMemberInfo가 실행되어야 함
        memberInfo = loginFunctions.getMemberInfo()
    }

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
            // This method is invoked on the main thread.
                call, result ->
            if (call.method == "getBatteryLevel") {
//                val batteryLevel = getBatteryLevel()
//
//                if (batteryLevel != -1) {
//                    result.success(batteryLevel)
//                } else {
//                    result.error("UNAVAILABLE", "Battery level not available.", null)
//                }
            } else {
                result.notImplemented()
            }
        }
    }
}

