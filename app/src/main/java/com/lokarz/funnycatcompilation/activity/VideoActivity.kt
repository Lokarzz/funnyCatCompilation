package com.lokarz.funnycatcompilation.activity

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.lokarz.funnycatcompilation.BuildConfig
import com.lokarz.funnycatcompilation.R
import com.lokarz.funnycatcompilation.Utils.ConstantUtil

class VideoActivity : YouTubeBaseActivity() {

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
        var youTubePlayerView = findViewById(R.id.ytpv) as YouTubePlayerView;

        youTubePlayerView.initialize(BuildConfig.GOOGLE_API_KEY, getOnInitializedListener());

    }

    fun getOnInitializedListener() : YouTubePlayer.OnInitializedListener {
        return object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
                Toast.makeText(baseContext, "Failed to initialize.", Toast.LENGTH_LONG).show();
            }

            override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, youTubePlayer: YouTubePlayer?, wasRestored: Boolean) {
                if (!wasRestored){
                    youTubePlayer?.loadVideo(mVideoUrl);
                }
            }

        }

    };

}
