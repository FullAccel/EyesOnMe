package com.example.fluttertest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fluttertest.alarm_package.AlarmRecyclerAdapter
import com.example.fluttertest.roomDB.AlarmDB
import com.example.fluttertest.roomDB.AlarmDataModel
import com.example.fluttertest.databinding.ActivityAlarmListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlarmListActivity : AppCompatActivity() {
    lateinit var listBinding: ActivityAlarmListBinding
    lateinit var adapter: AlarmRecyclerAdapter

    private val coroutineScope by lazy { CoroutineScope(Dispatchers.IO) }

    var data:ArrayList<AlarmDataModel> = ArrayList()
    lateinit var db: AlarmDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listBinding = ActivityAlarmListBinding.inflate(layoutInflater)
        setContentView(listBinding.root)
        init()
    }

    fun init() {
        db = AlarmDB.getDatabase(applicationContext)
        listBinding.recyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)
        adapter = AlarmRecyclerAdapter(data)

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


}