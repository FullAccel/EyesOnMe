package com.example.eom_fe.activities

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import com.example.eom_fe.R
import com.example.eom_fe.follow.KeepService
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class BackgroundActivity : FlutterActivity() {
    // 플러터 엔진을 초기화하고 YourTargetScreen.dart를 불러오는 로직 추가
    private val CHANNEL1 = "samples.flutter.dev/battery"  // 원하는 채널명으로 변경
    lateinit var serviceIntent: Intent
    var isFinished: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("eyesonme-BA", "BackgroundActivity called")

        val intent = intent
        if (intent != null) {
            val alarmCode = intent.getIntExtra("alarm_code", 0)
            val alarmType = intent.getIntExtra("alarm_type", 0)
            if (alarmCode != 0) {
                // alarmCode를 사용하여 필요한 작업 수행
                val arguments = HashMap<String, Any>()
                arguments["alarmCode"] = alarmCode // 정수 값을 추가
                arguments["alarmType"] = alarmType // 정수 값을 추가
//                playAlarmSound(alarmType)

                val gson = Gson()
                val jsonString = gson.toJson(arguments) // 맵을 JSON 문자열로 변환

                Log.d("eyesonme-BA", "serviceIntent started w/ onCreate()")
                flutterEngine?.dartExecutor?.binaryMessenger?.let {
                    MethodChannel(it, CHANNEL1).apply {
                        invokeMethod("showExtraScreen", jsonString)
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        val intent = intent
        if (intent != null) {
            val alarmCode = intent.getIntExtra("alarm_code", 0)
            val alarmType = intent.getIntExtra("alarm_type", 0)
            if (alarmCode != 0) {
                // alarmCode를 사용하여 필요한 작업 수행
                val arguments = HashMap<String, Any>()
                arguments["alarmCode"] = alarmCode // 정수 값을 추가
                arguments["alarmType"] = alarmType // 정수 값을 추가
//                playAlarmSound(alarmType)

                val gson = Gson()
                val jsonString = gson.toJson(arguments) // 맵을 JSON 문자열로 변환

                Log.d("eyesonme-BA", "serviceIntent started w/ onCreate()")
                flutterEngine?.dartExecutor?.binaryMessenger?.let {
                    MethodChannel(it, CHANNEL1).apply {
                        invokeMethod("showExtraScreen", jsonString)
                    }
                }
            }
        }
    }

    private val CHANNEL2 = "samples.flutter.dev/accomplish"  // 원하는 채널명으로 변경

//    override fun onBackPressed() {
////        super.onBackPressed()
////        if (isFinished) {
////            finish()
////        }
//    }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL2).setMethodCallHandler {
            // This method is invoked on the main thread.
                call, result ->
            if (call.method == "missionComplete") {

                if (mBound) {
                    mService.stopService()
                }
                isFinished = true

                Log.d("eyesonme-BA", "serviceIntent stopped")

            }
            else {
                result.notImplemented()
            }
        }
    }

//    override fun onBackPressed() {
//        if (isFinished) {
//            finish()
//        }
//    }

    private lateinit var mService: KeepService
    private var mBound: Boolean = false

    /** Defines callbacks for service binding, passed to bindService()  */
    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            val binder = service as KeepService.LocalBinder
            mService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
//            stopAlarmSound()
            mBound = false
        }
    }
    override fun onStart() {
        super.onStart()
        // Bind to KeepService
        Intent(this, KeepService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        mBound = false
    }

    private var mediaPlayer: MediaPlayer? = null

    fun playAlarmSound(type: Int) {
        try {
            setSoundMode(type)
            val alert: Uri = Uri.parse("android.resource://" + context.packageName + "/" + R.raw.inspiring)
            mediaPlayer = MediaPlayer.create(context, alert)
            mediaPlayer?.isLooping = true
            mediaPlayer?.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun stopAlarmSound() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.stop()
                it.release()
                mediaPlayer = null
            }
        }
    }

    fun setSoundMode(type: Int) {

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