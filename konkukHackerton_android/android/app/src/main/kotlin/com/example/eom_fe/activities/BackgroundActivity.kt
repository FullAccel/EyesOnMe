package com.example.eom_fe.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.plugin.common.MethodChannel

class BackgroundActivity : FlutterActivity() {
    // 플러터 엔진을 초기화하고 YourTargetScreen.dart를 불러오는 로직 추가
    private val CHANNEL = "samples.flutter.dev/battery"  // 원하는 채널명으로 변경

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("welcome", "BackgroundActivity called")
        flutterEngine?.dartExecutor?.binaryMessenger?.let {
            MethodChannel(it, CHANNEL).apply {
                invokeMethod("showExtraScreen", null)
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.d("welcome", "BackgroundActivity called")
        flutterEngine?.dartExecutor?.binaryMessenger?.let {
            MethodChannel(it, CHANNEL).apply {
                invokeMethod("showExtraScreen", null)
            }
        }
    }
}