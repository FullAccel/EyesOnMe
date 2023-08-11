package com.example.eom_fe.activities

import android.app.Application
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eom_fe.alarm_package.AlarmFunctions
import com.example.eom_fe.alarm_package.AlarmRecyclerAdapter
import com.example.eom_fe.data.ToDoData
import com.example.eom_fe.roomDB.AlarmDB
import com.example.eom_fe.roomDB.AlarmDataModel
import com.example.eom_fe.databinding.ActivityAlarmListBinding
import com.example.eom_fe.functions.DataFunctions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlarmListActivity : AppCompatActivity() {
    lateinit var listBinding: ActivityAlarmListBinding
    lateinit var adapter: AlarmRecyclerAdapter

    private val coroutineScope by lazy { CoroutineScope(Dispatchers.IO) }

    var data:ArrayList<AlarmDataModel> = ArrayList()
    lateinit var db: AlarmDB


    lateinit var dataFunctions: DataFunctions
    lateinit var alarmFunctions: AlarmFunctions

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listBinding = ActivityAlarmListBinding.inflate(layoutInflater)
        setContentView(listBinding.root)
        dataFunctions = MainActivity.mContext?.let { DataFunctions(it, applicationContext) }!!
        alarmFunctions = AlarmFunctions(MainActivity.mContext!!)
        MainActivity.mInfo?.let { dataFunctions.init(it) }
        init()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun init() {
        db = AlarmDB.getDatabase(applicationContext)
        listBinding.recyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)
        adapter = AlarmRecyclerAdapter(data)

        listBinding.addAlarmBtn.setOnClickListener() {
            val hour = listBinding.timePicker.hour.toString()
            val minute = listBinding.timePicker.minute.toString()
            val time = "2023-08-12 $hour:$minute:00" // 알람이 울리는 시간

            val content = listBinding.alarmText.text.toString()
//            setAlarm(alarmCode, content, time)

            coroutineScope.launch(Dispatchers.IO) {
//                db.alarmDao().addAlarm(AlarmDataModel(alarmCode, alarmCode, time, content))
                val todo = ToDoData(0, content, "C", time, time, "C001")
                dataFunctions.postTodoDataFunc("20230812", todo, true, 0)
                data = db.alarmDao().getAllAlarms() as ArrayList<AlarmDataModel>
                Log.d("alarmNotification", "getAllAlarm (MainActivity) - ${data.size}")
                launch(Dispatchers.Main) {
                    Log.d("alarmNotification", "getAllAlarm - launch executed")
                    getAllAlarm()
                }
            }
        }

        adapter.itemClickListener = object : AlarmRecyclerAdapter.OnItemClickListener {
            override fun OnItemClick(position: Int, item: AlarmDataModel) {
                data.remove(item)
                alarmFunctions.cancelAlarm(item.alarm_code)
                coroutineScope.launch(Dispatchers.IO) {
                    db.alarmDao().deleteAlarm(item.alarm_code)
                    dataFunctions.deleteTodoDataFunc(item.alarm_code)
                    launch(Dispatchers.Main) {
                        adapter.notifyItemRemoved(position)
                    }

                }

                Log.d("welcome", "delete alarm : ${item.alarm_code}")
            }
        }

        listBinding.recyclerView.adapter = adapter
        getAllAlarm()
    }

    fun getAllAlarm() {
        coroutineScope.launch(Dispatchers.IO) {
            data = db.alarmDao().getAllAlarms() as ArrayList<AlarmDataModel>
            adapter.items = data
            Log.d("alarmNotification", "getAllAlarm executed")
            Log.d("alarmNotification", "getAllAlarm executed")
            launch(Dispatchers.Main) {
                adapter.notifyDataSetChanged()
                Log.d("alarmNotification", "getAllAlarm - launch executed")
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setAlarm(alarmCode : Int, content : String, time : String){
        alarmFunctions.callAlarm(time, alarmCode, content)
    }

}