package com.phonebuyer.mossinwkung.mobilephonerguild

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.ActionBar
import android.support.v7.app.AlertDialog
import android.view.View
import android.view.Window
import android.widget.Toast
import com.phonebuyer.mossinwkung.mobilephonerguild.presentation.MobileListFragment
import kotlinx.android.synthetic.main.custome_action_bar_layout.*

class MainActivity : AppCompatActivity() {
    private var fragmentManager = supportFragmentManager
    private var alertDialog: AlertDialog? = null
    private var choice = arrayOf("Price low to high ", "Price high to low ", "Rating 5-1")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setCustomView(R.layout.custome_action_bar_layout)

        action_bar_back.visibility = View.GONE
        action_bar_forward.setOnClickListener {
            createDialog()
        }

        var mobileListFragment = MobileListFragment.Companion.newInstance()
        setupFragment(mobileListFragment)

    }


    fun setupFragment(fragment: Fragment) {
        var fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.contentFrame, fragment)
        fragmentTransaction.addToBackStack(fragment.toString())
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        fragmentTransaction.commit()
    }

    private fun createDialog() {
        var builder = AlertDialog.Builder(this)
        var fragment = supportFragmentManager.findFragmentById(R.id.contentFrame) as MobileListFragment
        builder.setSingleChoiceItems(choice, -1, DialogInterface.OnClickListener { dialog, item ->

            fragment.filterList(item)
            alertDialog?.dismiss()
        })
        alertDialog = builder.create()
        alertDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertDialog?.show()
    }
}
