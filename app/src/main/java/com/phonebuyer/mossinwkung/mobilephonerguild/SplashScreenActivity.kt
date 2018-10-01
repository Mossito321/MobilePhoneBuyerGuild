package com.phonebuyer.mossinwkung.mobilephonerguild

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.os.Handler


class SplashScreenActivity : Activity() {

    var SPLASH_DISPLAY_LENGTH = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val mainIntent = Intent(this, MainActivity::class.java)
            this.startActivity(mainIntent)
            this.finish()
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }
}