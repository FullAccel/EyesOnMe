package com.example.fluttertest.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.fluttertest.R

class WebViewActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    companion object {
        const val KAKAO_LOGIN_URL = "https://konkuk-hackerton.fly.dev/oauth2/authorization/kakao"
        const val GOOGLE_LOGIN_URL = "https://konkuk-hackerton.fly.dev/oauth2/authorization/google"
        const val SUCCESS_URL = "https://konkuk-hackerton.fly.dev/loginSuccess"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        webView = findViewById(R.id.webView)

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                // 페이지를 웹뷰에서 로드하도록 처리
                view?.loadUrl(request?.url.toString())
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                // 카카오 로그인에 성공하면 로그인 성공 화면이 나오는 URL을 체크하고 플러터 앱으로 결과 전달
                if (url == SUCCESS_URL) {
                    val resultIntent = Intent()
                    resultIntent.putExtra("loginData", "success")
                    // 로그인 성공 결과를 플러터 앱으로 전달
                    setResult(RESULT_OK, resultIntent)
                    finish() // WebViewActivity 종료
                }
            }
        }

        webView.settings.javaScriptEnabled = true

        // 웹뷰에 원하는 URL 로드
        webView.loadUrl(KAKAO_LOGIN_URL)
    }
}