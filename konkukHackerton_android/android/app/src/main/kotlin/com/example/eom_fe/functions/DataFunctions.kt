package com.example.eom_fe.functions

import android.content.Context
import android.util.Log
import com.example.eom_fe.api.RetrofitBuilder
import com.example.eom_fe.data.APIResponseData
import com.example.eom_fe.data.DailyPlanData
import com.example.eom_fe.data.MemberData
import com.example.eom_fe.data.ToDoData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.EventChannel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class DataFunctions (context: Context, applicationContext: Context) {

    val mainContext = context
    val applicationContext = applicationContext

    var memberInfo: MemberData = MemberData(0, "", "", "", 0, 0, "")

    private lateinit var eventChannel: EventChannel

    fun init(memberData: MemberData) {
//        memberInfo initial 필요
        memberInfo = memberData
    }

    // date : yyyymmdd 형식 (ex. 20230811)
    fun addDailyPlanFunc(date: String) {
        val addDailyPlanBuilder = RetrofitBuilder.api.addDailyPlan(memberInfo.id, date)
        addDailyPlanBuilder.enqueue(object: Callback<APIResponseData> {
            override fun onResponse(
                call: Call<APIResponseData>,
                response: Response<APIResponseData>
            ) {
                if (response.isSuccessful) {
                    Log.d("dataFunctions", "response : ${response.body()}")
                    val temp = response.body() as APIResponseData
                    val type: Type = object : TypeToken<Int>() {}.type
                    val jsonResult = Gson().toJson(temp.data)
                    val result = Gson().fromJson(jsonResult, type) as Int
                }
            }
            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
                // todo
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
                    Log.d("dataFunctions", "response : ${response.body()}")
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
                    Log.d("dataFunctions", "response : ${response.body()}")
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

    fun getMonthlyPlanFunc(callback: (List<DailyPlanData>?) -> Unit) {
        val getMonthlyPlanBuilder = RetrofitBuilder.api.getMontlyPlan(memberInfo.id)
        getMonthlyPlanBuilder.enqueue(object : Callback<APIResponseData> {
            override fun onResponse(
                call: Call<APIResponseData>,
                response: Response<APIResponseData>
            ) {
                if (response.isSuccessful) {
                    Log.d("dataFunctions", "response : ${response.body()}")
                    val temp = response.body() as APIResponseData
                    val type: Type = object : TypeToken<List<DailyPlanData>>() {}.type
                    val jsonResult = Gson().toJson(temp.data)
                    val result = Gson().fromJson(jsonResult, type) as List<DailyPlanData>
                    callback(result)
                }
                else callback(null)
            }

            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
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
        getMonthlyPlanFunc { result ->
            if (result != null) {
                // 작업 결과 사용
                for (day in result) {
                    if (day.yearMonth == fyearMonth && day.date == fdate) {
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
    fun runDailyPlansByDate(date: String) = runBlocking {
        val date = date
        val flow: Flow<List<ToDoData>> = getDailyPlansByDate(date)
        flow.collect { data ->
            println("Received data: $data")
        }
    }

    fun addTodoDataFunc(dailyPlanId: Int, todo: ToDoData) {
        val addTodoDataBuilder = RetrofitBuilder.api.addTodoData(dailyPlanId, todo)
        addTodoDataBuilder.enqueue(object : Callback<APIResponseData> {
            override fun onResponse(
                call: Call<APIResponseData>,
                response: Response<APIResponseData>
            ) {
                if (response.isSuccessful) {
                    Log.d("dataFunctions", "response : ${response.body()}")
                    val temp = response.body() as APIResponseData
                    val type: Type = object : TypeToken<Int>() {}.type
                    val jsonResult = Gson().toJson(temp.data)
                    val result = Gson().fromJson(jsonResult, type) as Int
                }
            }

            override fun onFailure(call: Call<APIResponseData>, t: Throwable) {
            }
        }
        )
    }

    fun editTodoDataFunc(todo: ToDoData) {
        val editTodoDataBuilder = RetrofitBuilder.api.editTodoData(todo.id, todo)
        editTodoDataBuilder.enqueue(object : Callback<APIResponseData> {
            override fun onResponse(
                call: Call<APIResponseData>,
                response: Response<APIResponseData>
            ) {
                if (response.isSuccessful) {
                    Log.d("dataFunctions", "response : ${response.body()}")
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

    fun getTodoDataFunc(todo: ToDoData) {
        val getTodoDataBuilder = RetrofitBuilder.api.getTodoData(todo.id)
        getTodoDataBuilder.enqueue(object : Callback<APIResponseData> {
            override fun onResponse(
                call: Call<APIResponseData>,
                response: Response<APIResponseData>
            ) {
                if (response.isSuccessful) {
                    Log.d("dataFunctions", "response : ${response.body()}")
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

    fun deleteTodoDataFunc(todo: ToDoData) {
        val deleteTodoDataBuilder = RetrofitBuilder.api.deleteTodoData(todo.id)
        deleteTodoDataBuilder.enqueue(object : Callback<APIResponseData> {
            override fun onResponse(
                call: Call<APIResponseData>,
                response: Response<APIResponseData>
            ) {
                if (response.isSuccessful) {
                    Log.d("dataFunctions", "response : ${response.body()}")
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

    suspend fun getDailyTodoDataFunc(dailyPlanId: Int): List<ToDoData>? {
        return suspendCancellableCoroutine { continuation ->
            val getDailyTodoDataBuilder = RetrofitBuilder.api.getDailyTodoData(dailyPlanId)
            getDailyTodoDataBuilder.enqueue(object : Callback<APIResponseData> {
                override fun onResponse(
                    call: Call<APIResponseData>,
                    response: Response<APIResponseData>
                ) {
                    if (response.isSuccessful) {
                        Log.d("dataFunctions", "response : ${response.body()}")
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


    fun completeTodoDataFunc(todo: ToDoData) {
        val completeTodoDataBuilder = RetrofitBuilder.api.completeTodoData(todo.id)
        completeTodoDataBuilder.enqueue(object : Callback<APIResponseData> {
            override fun onResponse(
                call: Call<APIResponseData>,
                response: Response<APIResponseData>
            ) {
                if (response.isSuccessful) {
                    Log.d("dataFunctions", "response : ${response.body()}")
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

    fun failureTodoDataFunc(todo: ToDoData) {
        val failureTodoDataBuilder = RetrofitBuilder.api.failureTodoData(todo.id)
        failureTodoDataBuilder.enqueue(object : Callback<APIResponseData> {
            override fun onResponse(
                call: Call<APIResponseData>,
                response: Response<APIResponseData>
            ) {
                if (response.isSuccessful) {
                    Log.d("dataFunctions", "response : ${response.body()}")
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

}