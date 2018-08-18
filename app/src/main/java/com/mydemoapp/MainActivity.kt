package com.mydemoapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mydemoapp.common.BaseActivity

class MainActivity : BaseActivity() {
    override fun defineLayoutResource(): Int {
        return R.layout.activity_main
    }

    override fun initToolbar() {
    }

    override fun initializeComponent() {
    }
}
