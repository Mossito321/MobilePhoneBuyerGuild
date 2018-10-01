package com.phonebuyer.mossinwkung.mobilephonerguild

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.custome_action_bar_layout.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setCustomView(R.layout.custome_action_bar_layout)
        var view = supportActionBar?.customView

        action_bar_back.visibility = View.GONE
        action_bar_forward.setOnClickListener {
            Toast.makeText(applicationContext,"Filter Button is clicked", Toast.LENGTH_LONG).show()
        }
    }
}
