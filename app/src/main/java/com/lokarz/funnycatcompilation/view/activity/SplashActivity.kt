package com.lokarz.funnycatcompilation.view.activity

import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.android.youtube.player.YouTubeBaseActivity
import com.lokarz.funnycatcompilation.R
import com.lokarz.funnycatcompilation.Utils.ViewUtil
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SplashActivity : YouTubeBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Glide.with(this).load(R.drawable.img_splash)
            .into(findViewById(R.id.iv_splash) as ImageView);

        Single.just(1).delay(3, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {
                ViewUtil.gotoScreen(this, HomeActivity::class.java)
            })
    }
}
