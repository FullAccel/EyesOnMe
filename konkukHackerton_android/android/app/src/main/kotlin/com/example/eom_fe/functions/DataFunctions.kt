package com.example.eom_fe.functions

import android.content.Context
import android.os.Build
import android.text.TextUtils.substring
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.eom_fe.activities.MainActivity
import com.example.eom_fe.alarm_package.AlarmFunctions
import com.example.eom_fe.api.RetrofitBuilder
import com.example.eom_fe.data.APIResponseData
import com.example.eom_fe.data.DailyPlanData
import com.example.eom_fe.data.MemberData
import com.example.eom_fe.data.ToDoData
import com.example.eom_fe.roomDB.AlarmDB
import com.example.eom_fe.roomDB.AlarmDataModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.flutter.plugin.common.EventChannel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DataFunctions (context: Context, applicationContext: Context) {

    val mainContext = context
    val applicationContext = applicationContext

//    var memberInfo: MemberData = MemberData(0, "", "", "", 0, 0, "")

    var memberInfo = MainActivity.mInfo!!

    private lateinit var eventChannel: EventChannel
    val alarmFunctions = AlarmFunctions(context)
    val db = AlarmDB.getDatabase(applicationContext)

    fun init(memberData: MemberData) {
//        memberInfo initial 필요
        memberInfo = memberData
        Log.d("eyesonme-DF", "init: $memberInfo")
    }

    // date : yyyymmdd 형식 (ex. 20230811)
    fun addDailyPlanFunc(date: String, callback: (Int?) -> Unit) {
        Log.d("eyesonme-DF", "addDailyPlanFunc called")
        Log.d("eyesonme-DF", "memberInfo: $memberInfo")
        val addDailyPlanBuilder = RetrofitBuilder.api.addDailyPlan(memberInfo.id, date)
        addDailyPlanBuilder.enqueue(object: Callback<APIResponseData> {
            override fun onResponse(
                call: Call<APIResponseData>,
                response: Response<APIResponseData>
            ) {
                if (response.isSuccessful) {
                    Log.d("eyesonme-DF", "response : ${response.body()}")
                    val temp = response.body() as APIResponseData
                    val type: Type = object : TypeToken<Int>() {}.type
                    val jsonResult = Gson().toJson(temp.data)
                    val result = Gson().fromJson(jsonResult, type) as Int
                    callback(result)
                }
                Log.d("eyesonme-DF", "addDailyPlanFunc null (1)")
                callback(null)
            }
            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
                Log.d("eyesonme-DF", "addDailyPlanFunc null (2)")
                callback(null)
            }
        }
        )
    }

    fun getDailyPlanFunc(dailyPlanId: Int) {
        val getDailyPlanBuilder = RetrofitBuilder.api.getDailyPlan(dailyPlanId)
        getDailyPlanBuilder.enqueue(object: Callback<APIResponseData> {
            override fun onResponse(
                call: Call<APIResponseData>,
                response: Response<APIResponseData>
            ) {
                if (response.isSuccessful) {
                    Log.d("eyesonme-DF", "response : ${response.body()}")
                    val temp = response.body() as APIResponseData
                    val type: Type = object : TypeToken<DailyPlanData>() {}.type
                    val jsonResult = Gson().toJson(temp.data)
                    val result = Gson().fromJson(jsonResult, type) as DailyPlanData
                }
            }

            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
                // todo
            }
        }
        )
    }

    fun deleteDailyPlanFunc(dailyPlanId: Int) {
        val deleteDailyPlanBuilder = RetrofitBuilder.api.deleteDailyPlan(dailyPlanId)
        deleteDailyPlanBuilder.enqueue(object : Callback<APIResponseData> {
            override fun onResponse(
                call: Call<APIResponseData>,
                response: Response<APIResponseData>
            ) {
                if (response.isSuccessful) {
                    Log.d("eyesonme-DF", "response : ${response.body()}")
                    val temp = response.body() as APIResponseData
                    val type: Type = object : TypeToken<Boolean>() {}.type
                    val jsonResult = Gson().toJson(temp.data)
                    val result = Gson().fromJson(jsonResult, type) as Boolean
                }
            }

            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
                // todo
            }
        }
        )
    }

    fun getMonthlyPlanFunc(yearMonth: String, callback: (List<DailyPlanData>?) -> Unit) {
        Log.d("eyesonme-DF", "getMonthlyPlanFunc started")
        val getMonthlyPlanBuilder = RetrofitBuilder.api.getMontlyPlan(memberInfo.id, yearMonth)
        getMonthlyPlanBuilder.enqueue(object : Callback<APIResponseData> {
            override fun onResponse(
                call: Call<APIResponseData>,
                response: Response<APIResponseData>
            ) {
                if (response.isSuccessful) {
                    Log.d("eyesonme-DF", "response : ${response.body()}")
                    val temp = response.body() as APIResponseData
                    val type: Type = object : TypeToken<List<DailyPlanData>>() {}.type
                    val jsonResult = Gson().toJson(temp.data)
                    val result = Gson().fromJson(jsonResult, type) as List<DailyPlanData>
                    callback(result)
                }
                Log.d("eyesonme-DF", "getMonthlyPlanFunc null (1)")
                callback(null)
            }

            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
                Log.d("eyesonme-DF", "getMonthlyPlanFunc null (2)")
                callback(null)
            }
        }
        )
    }

    // findDailyPlanId
    // date : yyyymmdd 형식 (ex. 20230811)
    fun findDailyPlanIdFunc(date: String, callback: (Int?) -> Unit) {
        val fyearMonth: String = date.take(6)
        val fdate: Int = date.takeLast(2).toInt()
        Log.d("eyesonme-DF", "findDailyPlanIdFunc : fYM: $fyearMonth, fdate: $fdate")
        getMonthlyPlanFunc(fyearMonth) { result ->
            if (result != null) {
                // 작업 결과 사용
                for (day in result) {
                    if (day.yearMonth.toInt() == fyearMonth.toInt() && day.date.toInt() == fdate) {
                        Log.d("eyesonme-DF", "findDailyPlanIdFunc : found it! ${day.yearMonth}, ${day.date.toInt()}")
                        callback(day.dailyPlanId)
                        return@getMonthlyPlanFunc
                    }
                }
            }
            callback(null)
        }
    }

    private suspend fun <T> asyncWithDeferred(block: suspend () -> T): CompletableDeferred<T> {
        val deferred = CompletableDeferred<T>()
        try {
            val result = block()
            deferred.complete(result)
        } catch (e: Exception) {
            deferred.completeExceptionally(e)
        }
        return deferred
    }

    // getDailyPlansByDate
    // date : yyyymmdd 형식 (ex. "20230811")
    fun getDailyPlansByDate(date: String): Flow<List<ToDoData>> = callbackFlow {
        findDailyPlanIdFunc(date) { dailyPlanId ->
            if (dailyPlanId != null) {
                GlobalScope.launch {
                    val dataDeferred = asyncWithDeferred {
                        getDailyTodoDataFunc(dailyPlanId)
                    }
                    val data = dataDeferred.await()!!
                    // 작업 결과를 Flow로 보내기
                    send(data)
                    close()
                }
            } else {
                // dailyPlanId가 없는 경우
                close()
            }
        }
        // Flow가 닫힐 때 취소하도록 등록
        awaitClose { }
    }

    // 실제 사용 함수!
    // 하루 모든 데일리플랜 얻기
    fun runDailyPlansByDate(date: String) = runBlocking {
        val date = date
        val flow: Flow<List<ToDoData>> = getDailyPlansByDate(date)
        flow.collect { data ->
            println("Received data: $data")
        }
    }


    // 실제 todo post
    @RequiresApi(Build.VERSION_CODES.M)
    fun postTodoDataFunc(date: String, todo: ToDoData, alarm: Boolean, aType: Int, aRepeat: Int) {
        Log.d("eyesonme-DF", "postTodoDataFunc called")
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("eyesonme-DF", "postTodoDataFunc : Coroutine scope started")
            findDailyPlanIdFunc(date) { dailyPlanId ->
                if (dailyPlanId != null) {
                    // post 하기
                    Log.d("eyesonme-DF", "postTodoDataFunc : dailyPlanId != null")
                    addTodoDataFunc(dailyPlanId, todo) { code ->
                        if (code != null && alarm) {
                            val time = todo.alarmStartTime // 알람이 울리는 시간

                            val content = todo.title

                            CoroutineScope(Dispatchers.IO).launch {
                                Log.d("eyesonme-DF", "postTodoDataFunc : !!CoroutineScope")
                                setAlarm(code, content, time)
                                db.alarmDao().addAlarm(AlarmDataModel(code, code, time, content, aType, aRepeat))
                            }
                        }
                    }
                } else {
                    // dailyPlanId가 없는 경우
                    Log.d("eyesonme-DF", "postTodoDataFunc : dailyPlanId == null")
                    addDailyPlanFunc(date) { dpId ->
                        if (dpId != null) {
                            addTodoDataFunc(dpId, todo) { code ->
                                if (code != null && alarm) {
                                    val time = todo.alarmStartTime // 알람이 울리는 시간

                                    val content = todo.title
                                    CoroutineScope(Dispatchers.IO).launch {
                                        Log.d("eyesonme-DF", "postTodoDataFunc : CoroutineScope")
                                        setAlarm(code, content, time)
                                        db.alarmDao().addAlarm(AlarmDataModel(code, code, time, content, aType, aRepeat))
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }

    fun addTodoDataFunc(dailyPlanId: Int, todo: ToDoData, callback: (Int?) -> Unit) {
        val addTodoDataBuilder = RetrofitBuilder.api.addTodoData(dailyPlanId, todo)
        addTodoDataBuilder.enqueue(object : Callback<APIResponseData> {
            override fun onResponse(
                call: Call<APIResponseData>,
                response: Response<APIResponseData>
            ) {
                if (response.isSuccessful) {
                    Log.d("eyesonme-DF", "response : ${response.body()}")
                    val temp = response.body() as APIResponseData
                    val type: Type = object : TypeToken<Int>() {}.type
                    val jsonResult = Gson().toJson(temp.data)
                    val result = Gson().fromJson(jsonResult, type) as Int
                    callback(result)
                }
                callback(null)
            }

            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
                callback(null)
            }
        }
        )
    }

    // 실제 Todo 수정으로 사용 가능
    fun editTodoDataFunc(todo: ToDoData, isAlarm: Boolean, aType: Int, aRepeat: Int) {
        val editTodoDataBuilder = RetrofitBuilder.api.editTodoData(todo.id, todo)
        editTodoDataBuilder.enqueue(object : Callback<APIResponseData> {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onResponse(
                call: Call<APIResponseData>,
                response: Response<APIResponseData>
            ) {
                if (response.isSuccessful) {
                    Log.d("eyesonme-DF", "response : ${response.body()}")
                    val temp = response.body() as APIResponseData
                    val type: Type = object : TypeToken<Boolean>() {}.type
                    val jsonResult = Gson().toJson(temp.data)
                    val result = Gson().fromJson(jsonResult, type) as Boolean
                    if (result) {
                        CoroutineScope(Dispatchers.IO).launch {
                            for (alarm in db.alarmDao().getAllAlarms()) {
                                if (alarm.alarm_code == todo.id) {
                                    alarmFunctions.cancelAlarm(alarm.alarm_code)
                                    db.alarmDao().deleteAlarm(alarm.alarm_code)
                                    if (isAlarm) {
                                        setAlarm(todo.id, todo.title, todo.alarmStartTime)
                                        db.alarmDao().addAlarm(AlarmDataModel(todo.id, todo.id, todo.alarmStartTime, todo.title, aType, aRepeat))
                                    }
                                    return@launch
                                }
                            }
                            if (isAlarm) {
                                setAlarm(todo.id, todo.title, todo.alarmStartTime)
                                db.alarmDao().addAlarm(AlarmDataModel(todo.id, todo.id, todo.alarmStartTime, todo.title, aType, aRepeat))
                                // alarmEndTime은?..
                                return@launch
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
            }
        }
        )
    }

    fun getTodoDataFunc(todoId: Int) {
        val getTodoDataBuilder = RetrofitBuilder.api.getTodoData(todoId)
        getTodoDataBuilder.enqueue(object : Callback<APIResponseData> {
            override fun onResponse(
                call: Call<APIResponseData>,
                response: Response<APIResponseData>
            ) {
                if (response.isSuccessful) {
                    Log.d("eyesonme-DF", "response : ${response.body()}")
                    val temp = response.body() as APIResponseData
                    val type: Type = object : TypeToken<ToDoData>() {}.type
                    val jsonResult = Gson().toJson(temp.data)
                    val result = Gson().fromJson(jsonResult, type) as ToDoData
                }
            }

            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
            }
        }
        )
    }

    // 실제 todo 지울 때 사용 가능
    fun deleteTodoDataFunc(todoId: Int) {
        val deleteTodoDataBuilder = RetrofitBuilder.api.deleteTodoData(todoId)
        deleteTodoDataBuilder.enqueue(object : Callback<APIResponseData> {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onResponse(
                call: Call<APIResponseData>,
                response: Response<APIResponseData>
            ) {
                if (response.isSuccessful) {
                    Log.d("eyesonme-DF", "response : ${response.body()}")
                    val temp = response.body() as APIResponseData
                    val type: Type = object : TypeToken<Boolean>() {}.type
                    val jsonResult = Gson().toJson(temp.data)
                    val result = Gson().fromJson(jsonResult, type) as Boolean
                    if (result) {
                        CoroutineScope(Dispatchers.IO).launch {
                            for (alarm in db.alarmDao().getAllAlarms()) {
                                if (alarm.alarm_code == todoId) {
                                    alarmFunctions.cancelAlarm(alarm.alarm_code)
                                    db.alarmDao().deleteAlarm(alarm.alarm_code)
                                    return@launch
                                }
                            }
                        }

                    }
                }
            }

            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
            }
        }
        )
    }

    suspend fun getDailyTodoDataFunc(dailyPlanId: Int): List<ToDoData>? {
        return suspendCancellableCoroutine { continuation ->
            val getDailyTodoDataBuilder = RetrofitBuilder.api.getDailyTodoData(dailyPlanId)
            getDailyTodoDataBuilder.enqueue(object : Callback<APIResponseData> {
                override fun onResponse(
                    call: Call<APIResponseData>,
                    response: Response<APIResponseData>
                ) {
                    if (response.isSuccessful) {
                        Log.d("eyesonme-DF", "response : ${response.body()}")
                        val temp = response.body() as APIResponseData
                        val type: Type = object : TypeToken<List<ToDoData>>() {}.type
                        val jsonResult = Gson().toJson(temp.data)
                        val result = Gson().fromJson(jsonResult, type) as List<ToDoData>
                        continuation.resume(result, null)
                    }
                    continuation.resume(null, null)
                }

                override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
                    continuation.resume(null, null)
                }
            }
            )
        }
    }

    // 실제 todo complete 시 사용 가능
    fun completeTodoDataFunc(todoId: Int) {
        val completeTodoDataBuilder = RetrofitBuilder.api.completeTodoData(todoId)
        completeTodoDataBuilder.enqueue(object : Callback<APIResponseData> {
            override fun onResponse(
                call: Call<APIResponseData>,
                response: Response<APIResponseData>
            ) {
                if (response.isSuccessful) {
                    Log.d("eyesonme-DF", "response : ${response.body()}")
                    val temp = response.body() as APIResponseData
                    val type: Type = object : TypeToken<Boolean>() {}.type
                    val jsonResult = Gson().toJson(temp.data)
                    val result = Gson().fromJson(jsonResult, type) as Boolean
                    // 알람 지워야 할까?
                }
            }

            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
            }
        }
        )
    }

    // 실제 todo failure 시 사용 가능
    fun failureTodoDataFunc(todoId: Int) {
        val failureTodoDataBuilder = RetrofitBuilder.api.failureTodoData(todoId)
        failureTodoDataBuilder.enqueue(object : Callback<APIResponseData> {
            override fun onResponse(
                call: Call<APIResponseData>,
                response: Response<APIResponseData>
            ) {
                if (response.isSuccessful) {
                    Log.d("eyesonme-DF", "response : ${response.body()}")
                    val temp = response.body() as APIResponseData
                    val type: Type = object : TypeToken<Boolean>() {}.type
                    val jsonResult = Gson().toJson(temp.data)
                    val result = Gson().fromJson(jsonResult, type) as Boolean
                    // 알람 만들어야 할까?
                }
            }

            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
            }
        }
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setAlarm(alarmCode : Int, content : String, time : String){
        alarmFunctions.callAlarm(time, alarmCode, content)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun setWakeAlarm(time: String, aType: Int, aRepeat: Int) {
//        val time = "2023-08-08 $hour:$minute:00" // 알람이 울리는 시간

        val random = (-100000..-1) // -100000 ~ -1 범위에서 알람코드 랜덤으로 생성
        var alarmCode = random.random()
        // 기상 alarmCode는 홀수, 음수
        if (alarmCode % 2 == 0) alarmCode++
        setAlarm(alarmCode, "기상", time)

        CoroutineScope(Dispatchers.IO).launch {
            db.alarmDao().addAlarm(AlarmDataModel(alarmCode, alarmCode, time, "기상", aType, aRepeat))
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun setSleepAlarm(time: String, aType: Int, aRepeat: Int) {
        val random = (-100000..-2) // -100000 ~ -2 범위에서 알람코드 랜덤으로 생성
        var alarmCode = random.random()
        // 취침 alarmCode는 짝수, 음수
        if (alarmCode % 2 != 0) alarmCode++
        setAlarm(alarmCode, "취침", time)

        CoroutineScope(Dispatchers.IO).launch {
            db.alarmDao().addAlarm(AlarmDataModel(alarmCode, alarmCode, time, "취침", aType, aRepeat))
        }
    }

    // date: yyyymmdd 형식
    suspend fun getWakeAlarm(date: String): AlarmDataModel? {
        return withContext(Dispatchers.IO) {
            for (alarm in db.alarmDao().getAllAlarms()) {
                val aDate = alarm.time.substring(0, 4) + alarm.time.substring(5, 7) + alarm.time.substring(8, 10)
                if (aDate == date && alarm.alarm_code % 2 != 0) {
                    return@withContext alarm
                }
            }
            return@withContext null
        }
    }

    suspend fun getSleepAlarm(date: String): AlarmDataModel? {
        return withContext(Dispatchers.IO) {
            for (alarm in db.alarmDao().getAllAlarms()) {
                val aDate = alarm.time.substring(0, 4) + alarm.time.substring(5, 7) + alarm.time.substring(8, 10)
                if (aDate == date && alarm.alarm_code % 2 == 0) {
                    return@withContext alarm
                }
            }
            return@withContext null
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun editWSAlarm(date: String, aType: Int, time: String, aRepeat: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            for (alarm in db.alarmDao().getAllAlarms()) {
                val aDate = alarm.time.substring(0, 4) + alarm.time.substring(5, 7) + alarm.time.substring(8, 10)
                if (aDate == date) {
                    val aCode = alarm.alarm_code
                    alarmFunctions.cancelAlarm(aCode)
                    db.alarmDao().deleteAlarm(aCode)
                    if (aCode % 2 == 0) {
                        // 취침
                        setAlarm(aCode, "취침", time)
                        db.alarmDao().addAlarm(AlarmDataModel(aCode, aCode, time, "취침", aType, aRepeat))
                    }
                    else {
                        setAlarm(aCode, "기상", time)
                        db.alarmDao().addAlarm(AlarmDataModel(aCode, aCode, time, "기상", aType, aRepeat))
                    }
                    return@launch
                }
            }
            // alarmEndTime은?..
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun delayAlarm(aCode: Int)  {
        CoroutineScope(Dispatchers.IO).launch {
            val preAlarm = db.alarmDao().getSingleAlarm(aCode)
            alarmFunctions.cancelAlarm(aCode)
            db.alarmDao().deleteAlarm(aCode)
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm:ss")
            val parsedTime = LocalDateTime.parse(preAlarm.time, formatter)
            val newTime = parsedTime.plusMinutes(preAlarm.repeat.toLong())
            val nTime = newTime.format(formatter)

            val nAlarm = AlarmDataModel(aCode, aCode, nTime, preAlarm.content, preAlarm.type, preAlarm.repeat)
            setAlarm(aCode, preAlarm.content, nTime)
            db.alarmDao().addAlarm(nAlarm)
        }
    }
}