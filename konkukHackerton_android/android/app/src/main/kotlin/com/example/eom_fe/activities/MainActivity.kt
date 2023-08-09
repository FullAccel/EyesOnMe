package com.example.eom_fe.activities

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

import com.example.eom_fe.R
import com.example.eom_fe.alarm_package.AlarmFunctions
import com.example.eom_fe.alarm_package.AlarmService
import com.example.eom_fe.data.MemberData
import com.example.eom_fe.functions.LoginFunctions
import com.example.eom_fe.roomDB.AlarmDB
import com.example.eom_fe.roomDB.AlarmDataModel
import com.kakao.sdk.common.KakaoSdk
import kotlinx.coroutines.*

class MainActivity: FlutterActivity() {
    private val CHANNEL = "samples.flutter.dev/battery"

    private val coroutineScope by lazy { CoroutineScope(Dispatchers.IO) }

    var data:ArrayList<AlarmDataModel> = ArrayList()
    lateinit var db: AlarmDB
    val alarmFunctions = AlarmFunctions(this)

    lateinit var memberInfo: MemberData
    lateinit var loginFunctions: LoginFunctions

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()

        db = AlarmDB.getDatabase(this)
        KakaoSdk.init(this, getString(R.string.kakao_hash_key))
        loginFunctions = LoginFunctions(this, applicationContext)

        // AlarmService : 앱 강종해도 종료되지 않고 계속 실행되도록 하는 서비스
        val fi = Intent(context, AlarmService::class.java)
        fi.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context?.startForegroundService(fi)

    }

    private fun initLogin() {
        Log.d("welcome", "initLogin()")
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
            // This method is invoked on the main thread.
                call, result ->
            if (call.method == "kakaoLogin") {
                memberInfo = loginFunctions.kakaoLogin()
                initLogin()

            else if (call.method == "showAlarmList") {
                val i = Intent(this, AlarmListActivity::class.java)
                startActivity(i)
            }
            else {
                result.notImplemented()
            }
        }
    }

}

