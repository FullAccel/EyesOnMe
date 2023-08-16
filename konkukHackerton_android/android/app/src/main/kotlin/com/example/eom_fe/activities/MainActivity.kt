package com.example.eom_fe.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import com.example.eom_fe.R
import com.example.eom_fe.alarm_package.AlarmFunctions
import com.example.eom_fe.alarm_package.AlarmService
import com.example.eom_fe.data.*
import com.example.eom_fe.functions.ChallengeFunctions
import com.example.eom_fe.functions.DataFunctions
import com.example.eom_fe.functions.LoginFunctions
import com.example.eom_fe.roomDB.AlarmDB
import com.example.eom_fe.roomDB.AlarmDataModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.template.model.Link
import com.kakao.sdk.template.model.TextTemplate
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
//import io.flutter.plugins.GeneratedPluginRegistrant
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MainActivity: FlutterActivity() {

    companion object {
        var memberId: Int? = null
        var mInfo: MemberData? = null
        @SuppressLint("StaticFieldLeak")
        var mContext: Context? = null

    }

    private val CHANNEL = "samples.flutter.dev/battery"

    private val coroutineScope by lazy { CoroutineScope(Dispatchers.IO) }

    var data:ArrayList<AlarmDataModel> = ArrayList()
    lateinit var db: AlarmDB
    val alarmFunctions = AlarmFunctions(this)
//    val dataFunctions = DataFunctions(this, )

//    lateinit var memberInfo: MemberData
    lateinit var loginFunctions: LoginFunctions
    lateinit var dataFunctions: DataFunctions
    lateinit var challengeFuntions: ChallengeFunctions

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()
//        Stetho.initializeWithDefaults(this)

        db = AlarmDB.getDatabase(applicationContext)
        val functions = AlarmFunctions(context)
        coroutineScope.launch {
            val db = AlarmDB.getDatabase(context)
            val list = db!!.alarmDao().getAllAlarms()
            val size = db.alarmDao().getAllAlarms().size
            list.let {
                for (i in 0 until size){
                    val time = list[i].time
                    val code = list[i].alarm_code
                    val type = list[i].type

                    // 현재 시간을 얻어옴
                    val currentTime = Calendar.getInstance()

                    // time 문자열을 Date 객체로 파싱
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd H:mm:ss", Locale.getDefault())
                    val timeDate = dateFormat.parse(time)

                    if (timeDate != null) {
                        val calendar = Calendar.getInstance()
                        calendar.time = timeDate

                        if (calendar.after(currentTime)) {
                            // time이 현재 시간보다 이후인 경우

                            val content = list[i].content
                            functions.callAlarm(time, code, content, type) // 알람 실행
                        } else {
                            db.alarmDao().deleteAlarm(code)
                        }
                    }
                }
            }
        }
        KakaoSdk.init(this, getString(R.string.kakao_hash_key))
        loginFunctions = LoginFunctions(this, applicationContext)
        dataFunctions = DataFunctions(this, applicationContext)
        challengeFuntions = ChallengeFunctions(this, applicationContext)
        mContext = context
        // AlarmService : 앱 강종해도 종료되지 않고 계속 실행되도록 하는 서비스
        val fi = Intent(context, AlarmService::class.java)
        fi.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startForegroundService(fi)

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
//        super.onWindowFocusChanged(hasFocus)

        Log.d("eyesonme-MA", "Focus changed !");

        if (!hasFocus) {
            Log.d("eyesonme-MA", "Lost focus !");

        }
    }

    fun getAllAlarm() {
        coroutineScope.launch(Dispatchers.IO) {
            data = db.alarmDao().getAllAlarms() as ArrayList<AlarmDataModel>
            Log.d("eyesonme-MA", "alarm-size: ${data.size}")
            for (alarm in data) {
                Log.d("eyesonme-MA", "alarm: $alarm")
            }
            Log.d("eyesonme-MA", "getAllAlarm executed")
        }
    }

    private fun initLogin() {
        Log.d("eyesonme-MA", "initLogin()")
        mInfo?.let { dataFunctions.init(it) }
        mInfo?.let { challengeFuntions.init(it) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
//        GeneratedPluginRegistrant.registerWith(flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
            // This method is invoked on the main thread.
                call, result ->
            when (call.method) {
                "kakaoLogin" -> {
                    FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                        if (!task.isSuccessful) {
                            Log.w("eyesonme-MA", "Fetching FCM registration token failed", task.exception)
                            return@OnCompleteListener
                        }

                        val token = task.result
                        val msg = token.toString()
//                        loginFunctions.kakaoLogin(msg)
                        loginFunctions.kakaoLogin(msg, object : LoginFunctions.KakaoLoginCallback {
                            override fun onLoginComplete(memberInfo: MemberData) {
                                Log.d("eyesonme-MA", msg)
                                initLogin()
                                getAllAlarm()
                                result.success("success")
                            }

                            override fun onLoginFailure(error: Throwable) {
                                // 로그인 실패 처리
                            }
                        })

                        // Get new FCM registration token


                        // 앱을 껐다 켜도 이 memberInfo가 유지되어야 함....
                        // 아니면 필요할 때마다 dataFunctions 만들고 init(memberInfo)로 초기화해도 똑같이 사용 가능
                    })

                }
                "getMemberData" -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        mInfo?.let { dataFunctions.init(it) }
                        result.success(Gson().toJson(mInfo).toString())
                    }
                }
                "getData" -> {
                    //
//                    runBlocking {
//                        val date = "20230811"
//                        val flow: Flow<List<ToDoData>> = dataFunctions.getDailyPlansByDate(date)
//                        flow.collect { data ->
//                            println("Received data: $data")
//                            // json으로 파싱하고 -> result.success로 보내기
//                        }
//                    }
        //                result.success(dataFunctions.runDailyPlansByDate("20230811"))

                }
                "testData" -> {
                    Log.d("eyesonme-MA", "arguments : ${call.arguments}")
                    result.success(Gson().toJson(mInfo).toString())
                }
                "showAlarmList" -> {
                    val i = Intent(this, AlarmListActivity::class.java)
                    startActivity(i)
                }
                "getAllDailyPlansByDate" -> {
                    var date = call.arguments as String
                    Log.d("eyesonme-MA", "wanna get today's plan : $date")
                    CoroutineScope(Dispatchers.IO).launch {
                        dataFunctions.getDailyPlansByDate(date) { data ->
                            // data를 사용하여 원하는 작업 수행
                            result.success(Gson().toJson(data).toString())
                        }
                    }

                }
                "postTodoDataFunc" -> {
                    val jsonString = call.arguments as String
                    val jsonObject = JSONObject(jsonString)

                    val title = jsonObject.getString("title")
                    // status -> "C", "P", "F" 중 하나
//                    val status = jsonObject.getString("status")
                    val startTime = jsonObject.getString("startTime")
                    val date = startTime.substring(0, 4) + startTime.substring(5, 7) + startTime.substring(8, 10)

                    val endTime = jsonObject.getString("endTime")
                    val cCode = jsonObject.getString("cCode")

                    val isAlarm = jsonObject.getBoolean("isAlarm")
                    // alarmType
                    // 0: 무음, 1: 진동, 2: 소리
                    val alarmType = jsonObject.getInt("alarmType")

                    val alarmRepeat = jsonObject.getInt("alarmRepeat")

                    coroutineScope.launch(Dispatchers.IO) {
                        val todo = ToDoData(0, title, "C", startTime, endTime, cCode)
                        Log.d("eyesonme-MA", "postTodoDataFunc called: date $date, todo $todo, isAlarm $isAlarm, alarmType $alarmType, alarmRepeat $alarmRepeat")
                        dataFunctions.postTodoDataFunc(date, todo, isAlarm, alarmType, alarmRepeat)
                        getAllAlarm()
                        result.success("success")
                    }


                }
                "getSingleTodoData" -> {
                    val jsonString = call.arguments as String
                    CoroutineScope(Dispatchers.IO).launch {
                        dataFunctions.getTodoDataFunc(jsonString.toInt()) { data ->
                            result.success(data)
                        }
                    }
                }

                "editTodoDataFunc" -> {
                    val jsonString = call.arguments as String

                    val gson = Gson()
                    val todoFData: ToDoFlutterData = gson.fromJson(jsonString, ToDoFlutterData::class.java)
                    CoroutineScope(Dispatchers.IO).launch {
                        dataFunctions.editTodoDataFunc(todoFData.toDoData, todoFData.isAlarm, todoFData.alarmType, todoFData.alarmRepeat)
                        result.success("success")
                    }
                }
                "deleteTodoDataFunc" -> {
                    val jsonString = call.arguments as String
                    CoroutineScope(Dispatchers.IO).launch {
                        dataFunctions.deleteTodoDataFunc(jsonString.toInt())
                        result.success("success")
                    }
                }
                "completeTodoDataFunc" -> {
                    val jsonString = call.arguments as String
                    CoroutineScope(Dispatchers.IO).launch {
                        dataFunctions.completeTodoDataFunc(jsonString.toInt())
                        result.success("success")
                    }
                }
                "failureTodoDataFunc" -> {
                    val jsonString = call.arguments as String
                    CoroutineScope(Dispatchers.IO).launch {
                        dataFunctions.failureTodoDataFunc(jsonString.toInt())
                        result.success("success")
                    }
                }
                "setWakeAlarm" -> {
                    val jsonString = call.arguments as String
                    val jsonObject = JSONObject(jsonString)

                    val startTime = jsonObject.getString("startTime")

                    // alarmType
                    // 0: 무음, 1: 진동, 2: 소리
                    val alarmType = jsonObject.getInt("alarmType")

                    val alarmRepeat = jsonObject.getInt("alarmRepeat")

                    CoroutineScope(Dispatchers.IO).launch {
                        dataFunctions.setWakeAlarm(startTime, alarmType, alarmRepeat)
                        result.success("success")
                    }
                }
                "setSleepAlarm" -> {
                    val jsonString = call.arguments as String
                    val jsonObject = JSONObject(jsonString)

                    val startTime = jsonObject.getString("startTime")

                    // alarmType
                    // 0: 무음, 1: 진동, 2: 소리
                    val alarmType = jsonObject.getInt("alarmType")

                    val alarmRepeat = jsonObject.getInt("alarmRepeat")

                    CoroutineScope(Dispatchers.IO).launch {
                        dataFunctions.setSleepAlarm(startTime, alarmType, alarmRepeat)
                        result.success("success")
                    }
                }
                "getWakeAlarm" -> {
                    var date = call.arguments as String

                    runBlocking {
                        val wakeAlarm = dataFunctions.getWakeAlarm(date)

                        if (wakeAlarm != null) {
                            println("Wake alarm found: $wakeAlarm")
                            result.success(Gson().toJson(wakeAlarm).toString())
                        } else {
                            println("No wake alarm found for the date: $date")
                        }
                    }
                }
                "getSleepAlarm" -> {
                    var date = call.arguments as String

                    runBlocking {
                        val sleepAlarm = dataFunctions.getSleepAlarm(date)

                        if (sleepAlarm != null) {
                            println("Sleep alarm found: $sleepAlarm")
                            result.success(Gson().toJson(sleepAlarm).toString())
                        } else {
                            println("No sleep alarm found for the date: $date")
                        }
                    }
                }
                "editWSAlarm" -> {
                    // date: String, aType: Int, time: String
                    val jsonString = call.arguments as String
                    val jsonObject = JSONObject(jsonString)

                    val startTime = jsonObject.getString("startTime")
                    val date = startTime.substring(0, 4) + startTime.substring(5, 7) + startTime.substring(8, 10)

                    // alarmType
                    // 0: 무음, 1: 진동, 2: 소리
                    val alarmType = jsonObject.getInt("alarmType")

                    val alarmRepeat = jsonObject.getInt("alarmRepeat")

                    CoroutineScope(Dispatchers.IO).launch {
                        dataFunctions.editWSAlarm(date, alarmType, startTime, alarmRepeat)
                        result.success("success")
                    }
                }
                "delayAlarm" -> {
                    val jsonString = call.arguments as String
                    CoroutineScope(Dispatchers.IO).launch {
                        dataFunctions.delayAlarm(jsonString.toInt())
                        result.success("success")
                    }
                }
                "delayWSAlarm" -> {
                    val jsonString = call.arguments as String
                    CoroutineScope(Dispatchers.IO).launch {
                        dataFunctions.delayWSAlarm(jsonString)
                        result.success("success")
                    }
                }

                // 여기서부터 챌린지 함수
                "getAllChallenges" -> {
                    Log.d("eyesonme-MA", "getAllChallenges called in Kotlin")
//                    CoroutineScope(Dispatchers.IO).launch {
//                        val challengesList = challengeFuntions.getAllChallengesFunc() // suspend 함수 호출
//                        result.success(Gson().toJson(challengesList).toString()) // 결과 출력 또는 처리
//                    }
                    CoroutineScope(Dispatchers.IO).launch {
                        challengeFuntions.getAllChallengesFunc() { data ->
                            // data를 사용하여 원하는 작업 수행
                            Log.d("eyesonme-MA", "challengeFunctions !!!!!")
                            result.success(Gson().toJson(data).toString())
                        }
                    }
                }
                "getAllValidators" -> {
                    val jsonString = call.arguments as String
                    CoroutineScope(Dispatchers.IO).launch {
                        val validatorList = challengeFuntions.getAllValidators(jsonString.toInt())
                        result.success(Gson().toJson(validatorList).toString())
                    }
                }
                "getSingleChallenge" -> {
                    val jsonString = call.arguments as String
                    CoroutineScope(Dispatchers.IO).launch {
                        val challenge = challengeFuntions.getChallengeDataFunc(jsonString.toInt()) // suspend 함수 호출
                        result.success(Gson().toJson(challenge).toString()) // 결과 출력 또는 처리
                    }
                }
//                "addValidator" -> {
//                    val jsonString = call.arguments as String
//
//                    val gson = Gson()
//                    val todoFData: AddValidatorListData = gson.fromJson(jsonString, AddValidatorListData::class.java)
//                    CoroutineScope(Dispatchers.IO).launch {
//                        challengeFuntions.addValidator(todoFData.challengeId, todoFData.validatorNameList)
//                        result.success("success")
//                    }
//                }
                /*
                "makeChallengeWithValidator" -> {
                    val jsonString = call.arguments as String

                    val gson = Gson()
                    val todoFData: ChallengeMakeData = gson.fromJson(jsonString, ChallengeMakeData::class.java)
                    CoroutineScope(Dispatchers.IO).launch {
                        if (todoFData.validatorNameList == null) {
                            challengeFuntions.makeChallengeWithValidator(todoFData.challengeRequestData, null)
                        }
                        else {
                            challengeFuntions.makeChallengeWithValidator(todoFData.challengeRequestData, todoFData.validatorNameList)
                        }

                        result.success("success")
                    }
                    // Gson().toJson(validatorList).toString()
                }
                 */
                "makeChallengeWOValidator" -> {
                    val jsonString = call.arguments as String

                    val gson = Gson()
                    val cd: ChallengeRequestData = gson.fromJson(jsonString, ChallengeRequestData::class.java)
                    CoroutineScope(Dispatchers.IO).launch {
                        challengeFuntions.addChallengeDataFunc(cd) { challengeId ->
                            result.success(challengeId.toString())
//                            if (challengeId != null) {
//                                // 결과 값인 challengeId를 사용하여 다른 작업 수행
//
//                            } else {
//                                // 실패한 경우 처리
//                            }
                        }

                    }
                }

                "editChallengeDataFunc" -> {
                    val jsonString = call.arguments as String

                    val gson = Gson()
                    val todoFData: ChallengeEditData = gson.fromJson(jsonString, ChallengeEditData::class.java)
                    CoroutineScope(Dispatchers.IO).launch {
                        challengeFuntions.editChallengeDataFunc(todoFData.challengeId, todoFData.challengeRequestData)
                        result.success("success")
                    }
                }

                "postValidationDiary" -> {
                    val jsonString = call.arguments as String
                    val jsonObject = JSONObject(jsonString)

                    val cId = jsonObject.getInt("id")
                    // status -> "C", "P", "F" 중 하나
//                    val status = jsonObject.getString("status")
                    val date = jsonObject.getString("date")

                    val writing = jsonObject.getString("writing")
                    val path = jsonObject.getString("path")

                    CoroutineScope(Dispatchers.IO).launch {
                        challengeFuntions.postChallengeImage(cId, date, writing, path) { accessUrl ->
                            result.success(accessUrl)
                        }
                    }
                }

                "sendKakaoValidationMessage" -> {
                    val jsonString = call.arguments as String
                    val jsonObject = JSONObject(jsonString)

                    val cId = jsonObject.getInt("id")
                    val title = jsonObject.getString("title")
                    val validationIntervalCode = jsonObject.getString("validationIntervalCode")
                    val validationCountPerInterval = jsonObject.getInt("validationCountPerInterval")

                    CoroutineScope(Dispatchers.IO).launch {
                        challengeFuntions.sendKakaoValidationMessage(cId, title, validationIntervalCode, validationCountPerInterval)
                        result.success("success")
                    }
                }

                "enterValidationWithLogin" -> {
                    val cId = call.arguments as String
                    CoroutineScope(Dispatchers.IO).launch {
                        challengeFuntions.enterValidation(cId.toInt())
                        result.success("success")
                    }
                }
                "deleteValidator" -> {
                    val jsonString = call.arguments as String
                    val jsonObject = JSONObject(jsonString)

                    val cId = jsonObject.getInt("challengeId")
                    val vId = jsonObject.getInt("validatorId")
                    CoroutineScope(Dispatchers.IO).launch {
                        challengeFuntions.deleteValidatorDataFunc(cId, vId)
                        result.success("success")
                    }
                }







                else -> {
                    result.notImplemented()
                }
            }
        }
    }

}

