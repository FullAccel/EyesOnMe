package com.example.eom_fe.activities

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
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
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class BackgroundActivity : FlutterActivity() {
    // 플러터 엔진을 초기화하고 YourTargetScreen.dart를 불러오는 로직 추가
    private val CHANNEL1 = "samples.flutter.dev/battery"  // 원하는 채널명으로 변경
    lateinit var serviceIntent: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("welcome", "BackgroundActivity called")
        flutterEngine?.dartExecutor?.binaryMessenger?.let {
            MethodChannel(it, CHANNEL1).apply {
                invokeMethod("showExtraScreen", null)
            }
        }

        Log.d("welcome", "serviceIntent started w/ onCreate()")
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        flutterEngine?.dartExecutor?.binaryMessenger?.let {
            MethodChannel(it, CHANNEL1).apply {
                invokeMethod("showExtraScreen", null)
            }
        }

        Log.d("welcome", "serviceIntent started w/ onNewIntent()")
    }

    private val CHANNEL2 = "samples.flutter.dev/accomplish"  // 원하는 채널명으로 변경

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

                Log.d("welcome", "serviceIntent stopped")

            }
            else {
                result.notImplemented()
            }
        }
    }

    override fun onBackPressed() {

    }

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
}