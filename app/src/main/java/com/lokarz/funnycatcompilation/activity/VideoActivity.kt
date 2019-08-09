package com.lokarz.funnycatcompilation.activity

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.lokarz.funnycatcompilation.R
import com.lokarz.funnycatcompilation.Utils.ConstantUtil

class VideoActivity : AppCompatActivity() {

    var mVideoUrl= "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video)

        mVideoUrl = intent.getStringExtra(ConstantUtil.URL);

        initView()
    }

    fun initView() {
        var vvHowItWorks: WebView = findViewById(R.id.wv_video);
        vvHowItWorks.setWebViewClient(object : WebViewClient() {});

        vvHowItWorks.setWebChromeClient(object : WebChromeClient() {});

        vvHowItWorks.getSettings().setDomStorageEnabled(true);
        vvHowItWorks.getSettings().setDatabaseEnabled(true);
        vvHowItWorks.getSettings().setJavaScriptEnabled(true);

        var url = String.format("https://www.youtube.com/embed/%s?loop=1&controls=1&playlist=%s&autoplay=1", mVideoUrl, mVideoUrl);

        vvHowItWorks.loadUrl(url);
    }
}
