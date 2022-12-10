package edu.miu.profileapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_webview.*

class WebviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        val urlString: String = intent.getSerializableExtra("url") as String

        web_view.settings.javaScriptEnabled = true;
        web_view.settings.builtInZoomControls = true;
        web_view.webViewClient = WebViewClient()
        web_view.loadUrl(urlString)
    }
}