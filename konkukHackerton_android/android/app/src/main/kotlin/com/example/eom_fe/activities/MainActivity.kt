package com.example.eom_fe.activities

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import com.example.eom_fe.R
import com.example.eom_fe.alarm_package.AlarmFunctions
import com.example.eom_fe.alarm_package.AlarmService
import com.example.eom_fe.data.MemberData
import com.example.eom_fe.data.ToDoData
import com.example.eom_fe.follow.CustomMessageFactory
import com.example.eom_fe.functions.DataFunctions
import com.example.eom_fe.functions.LoginFunctions
import com.example.eom_fe.roomDB.AlarmDB
import com.example.eom_fe.roomDB.AlarmDataModel
import com.facebook.stetho.Stetho
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import com.kakao.sdk.common.KakaoSdk
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugins.GeneratedPluginRegistrant
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.suspendCoroutine


class MainActivity: FlutterActivity() {

    companion object {
        var memberId: Int? = null
        var mInfo: MemberData? = null
        var mContext: Context? = null

    }

    private val CHANNEL = "samples.flutter.dev/battery"

    private val coroutineScope by lazy { CoroutineScope(Dispatchers.IO) }

    var data:ArrayList<AlarmDataModel> = ArrayList()
    lateinit var db: AlarmDB
    val alarmFunctions = AlarmFunctions(this)
//    val dataFunctions = DataFunctions(this, )

    lateinit var memberInfo: MemberData
    lateinit var loginFunctions: LoginFunctions
    lateinit var dataFunctions: DataFunctions

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()
//        Stetho.initializeWithDefaults(this)

        db = AlarmDB.getDatabase(applicationContext)
        KakaoSdk.init(this, getString(R.string.kakao_hash_key))
        loginFunctions = LoginFunctions(this, applicationContext)
        dataFunctions = DataFunctions(this, applicationContext)
        mContext = context
        // AlarmService : 앱 강종해도 종료되지 않고 계속 실행되도록 하는 서비스
        val fi = Intent(context, AlarmService::class.java)
        fi.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context?.startForegroundService(fi)

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
//        super.onWindowFocusChanged(hasFocus)

        Log.d("welcome", "Focus changed !");

        if (!hasFocus) {
            Log.d("welcome", "Lost focus !");

        }
    }

    private fun initLogin() {
        Log.d("welcome", "initLogin()")
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
//        flutterEngine.platformViewsController
//            .registry
//            .registerViewFactory(
//                "CustomMessageFactory",
//                CustomMessageFactory(activity, MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL))
//            )
        GeneratedPluginRegistrant.registerWith(flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
            // This method is invoked on the main thread.
                call, result ->
            if (call.method == "kakaoLogin") {
                FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w("tokennnn", "Fetching FCM registration token failed", task.exception)
                        return@OnCompleteListener
                    }

                    // Get new FCM registration token
                    val token = task.result

                    // Log and toast
                    val msg = token.toString()
                    memberInfo = loginFunctions.kakaoLogin(msg)
                    memberId = memberInfo.id

                    // 앱을 껐다 켜도 이 memberInfo가 유지되어야 함....
                    // 아니면 필요할 때마다 dataFunctions 만들고 init(memberInfo)로 초기화해도 똑같이 사용 가능
                    dataFunctions.init(memberInfo)
                    initLogin()
                    Log.d("tokennnn", msg)
//            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                })

            }
            else if (call.method == "getData") {
                //
                runBlocking {
                    val date = "20230811"
                    val flow: Flow<List<ToDoData>> = dataFunctions.getDailyPlansByDate(date)
                    flow.collect { data ->
                        println("Received data: $data")
                        // json으로 파싱하고 -> result.success로 보내기
                    }
                }
//                result.success(dataFunctions.runDailyPlansByDate("20230811"))

            }
            else if (call.method == "testData") {
                Log.d("testData", "arguments : ${call.arguments}")
                result.success(Gson().toJson(mInfo).toString())
            }
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

