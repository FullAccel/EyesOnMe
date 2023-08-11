package com.example.eom_fe.alarm_package

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.net.ParseException
import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.text.SimpleDateFormat
import java.util.*

class AlarmFunctions(private val context: Context){

    private lateinit var pendingIntent: PendingIntent
    private val ioScope by lazy { CoroutineScope(Dispatchers.IO) }

    @RequiresApi(Build.VERSION_CODES.M)
    fun callAlarm(time : String, alarm_code : Int, content : String){

        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val receiverIntent = Intent(context, AlarmReceiver::class.java) //리시버로 전달될 인텐트 설정
        receiverIntent.apply {
            putExtra("alarm_rqCode", alarm_code) //요청 코드를 리시버에 전달
            putExtra("content", content) //수정_일정 제목을 리시버에 전달
        }

        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            PendingIntent.getBroadcast(context,alarm_code,receiverIntent,PendingIntent.FLAG_IMMUTABLE)
        }else{
            PendingIntent.getBroadcast(context,alarm_code,receiverIntent,PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val dateFormat = SimpleDateFormat("yyyy-MM-dd H:mm:ss")
        var datetime = Date()
        try {
            datetime = dateFormat.parse(time) as Date
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        val calendar = Calendar.getInstance()
        calendar.time = datetime

        //API 23(android 6.0) 이상(해당 api 레벨부터 도즈모드 도입으로 setExact 사용 시 알람이 울리지 않음)
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,pendingIntent);
    }

    fun cancelAlarm(alarm_code: Int) {
        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)

        pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            PendingIntent.getBroadcast(context,alarm_code,intent,PendingIntent.FLAG_IMMUTABLE)
        } else{
            PendingIntent.getBroadcast(context,alarm_code,intent,PendingIntent.FLAG_UPDATE_CURRENT)
        }

        alarmManager.cancel(pendingIntent)
    }

    fun playSound(type: Int) {

        /*
        if type == 0 -> 무음
        else if type == 1 -> 진동
        else (type == 2) -> 소리
         */

        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when (type) {
                0 -> {
                    if (audioManager.ringerMode == AudioManager.RINGER_MODE_VIBRATE || audioManager.ringerMode == AudioManager.RINGER_MODE_NORMAL) {
                        audioManager.ringerMode = AudioManager.RINGER_MODE_SILENT
                    }
                }
                1 -> {
                    if (audioManager.ringerMode == AudioManager.RINGER_MODE_NORMAL || audioManager.ringerMode == AudioManager.RINGER_MODE_SILENT) {
                        audioManager.ringerMode = AudioManager.RINGER_MODE_VIBRATE
                    }
                }
                2 -> {
                    if (audioManager.ringerMode == AudioManager.RINGER_MODE_SILENT || audioManager.ringerMode == AudioManager.RINGER_MODE_VIBRATE) {
                        // 무음 모드나 진동 모드인 경우 해제하여 소리 울리게 하기
                        audioManager.ringerMode = AudioManager.RINGER_MODE_NORMAL
                    }
                }
            }

        } else {
            when (type) {
                0 -> {
                    if (audioManager.ringerMode == AudioManager.RINGER_MODE_NORMAL) {
                        audioManager.ringerMode = AudioManager.RINGER_MODE_SILENT
                    }
                }
                1 -> {
                    if (audioManager.ringerMode == AudioManager.RINGER_MODE_NORMAL) {
                        audioManager.ringerMode = AudioManager.RINGER_MODE_SILENT
                    }
                }
                2 -> {
                    if (audioManager.ringerMode == AudioManager.RINGER_MODE_SILENT) {
                        // 무음 모드인 경우 해제하여 소리 울리게 하기
                        audioManager.ringerMode = AudioManager.RINGER_MODE_NORMAL
                    }
                }
            }
        }

        // 소리를 울릴 수 있는 알림(혹은 미디어)을 실행하는 코드 작성
        // 여기서는 알림(Notification)을 사용하는 경우를 예로 들어 작성하지만, 필요에 따라 다른 방법으로 소리를 울릴 수 있습니다.
        // 알림에 사용할 소리를 등록하고 알림을 실행하는 코드 작성
    }
}