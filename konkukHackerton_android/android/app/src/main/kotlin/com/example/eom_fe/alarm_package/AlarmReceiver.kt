package com.example.eom_fe.alarm_package

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.CanceledException
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.ContextCompat.startForegroundService
import com.example.eom_fe.R
import com.example.eom_fe.activities.AlarmListActivity
import com.example.eom_fe.activities.BackgroundActivity
import com.example.eom_fe.activities.MainActivity
import com.example.eom_fe.follow.KeepService


class AlarmReceiver() : BroadcastReceiver() {

    private lateinit var manager: NotificationManager
    private lateinit var builder: NotificationCompat.Builder

    //오레오 이상은 반드시 채널을 설정해줘야 Notification 작동함
    companion object{
        const val CHANNEL_ID = "channel"
        const val CHANNEL_NAME = "channel1"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onReceive(context: Context?, intent: Intent?) {

        Log.d("eyesonme-AR", "onReceived called...")

        val serviceIntent = Intent(context, KeepService::class.java)
        if (intent != null) {
            val alarmRqCode = intent.getIntExtra("alarm_rqCode", 0)
            if (alarmRqCode != 0) {
                // alarmRqCode를 사용하여 필요한 작업 수행
                serviceIntent.putExtra("alarm_code", alarmRqCode)
                context?.startService(serviceIntent)
            }
        }

//        val i = Intent(context, BackgroundActivity::class.java)
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        context?.startActivity(i)

    }


}