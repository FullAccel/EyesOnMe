package com.example.eom_fe.alarm_package

import com.example.eom_fe.roomDB.AlarmDB
import com.example.eom_fe.roomDB.AlarmDataModel

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestartAlarmReceiver : BroadcastReceiver() {

    private val coroutineScope by lazy { CoroutineScope(Dispatchers.IO) }
    private lateinit var functions: AlarmFunctions
    var data:ArrayList<AlarmDataModel> = ArrayList()
    lateinit var db: AlarmDB

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.equals("android.intent.action.BOOT_COMPLETED")) {
            functions = AlarmFunctions(context)
            coroutineScope.launch {
                val db = AlarmDB.getDatabase(context)
                val list = db!!.alarmDao().getAllAlarms()
                val size = db.alarmDao().getAllAlarms().size
                list.let {
                    for (i in 0 until size){
                        val time = list[i].time
                        val code = list[i].alarm_code
                        val content = list[i].content
                        val type = list[i].type
                        functions.callAlarm(time, code, content, type) // 알람 실행
                    }
                }
            }
        }
    }
}