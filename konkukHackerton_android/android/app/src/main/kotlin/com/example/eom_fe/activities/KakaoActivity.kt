package com.example.eom_fe.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.plugin.common.MethodChannel

class KakaoActivity : FlutterActivity() {

    private val CHANNEL1 = "samples.flutter.dev/battery"  // 원하는 채널명으로 변경

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val bundle = intent.extras

        initDeepLink()
    }

    private fun initDeepLink() {
        if (Intent.ACTION_VIEW == intent.action) {
            val uri = intent.data
            Log.d("eyesonme-KA", "intent.data = ${intent.data}")
            if (uri != null) {
                // ⭐️여기서 androidExecutionParams 값들을 받아와 어떠한 상세페이지를 띄울지 결정할 수 있음
                val cIdString = uri.getQueryParameter("cIdString")
                Log.d("eyesonme-KA", "cIdString : $cIdString")
                flutterEngine?.dartExecutor?.binaryMessenger?.let {
                    MethodChannel(it, CHANNEL1).apply {
                        invokeMethod("getKakaoValidationMessage", cIdString)
                    }
                }

//                val bundle = Bundle()
//                if (cIdString != null) {
//                    bundle.putInt("cIdString", cIdString.toInt())
//                    togetherFragment.arguments = bundle
//                    benefitFragment.arguments = bundle
//
//                    if (payMode == "benefit") {
//                        //benefitFragment로 이동
//                    } else {
//                        //benefitFragment로 이동
//                    }
//                }
            }
        }
    }
}