package com.mydemoapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mydemoapp.common.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun defineLayoutResource(): Int {
        return R.layout.activity_main
    }

    override fun initToolbar() {
    }

    override fun initializeComponent() {
        ivSearch.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        super.onClick(view)

        when (view.id) {
            R.id.ivSearch -> {

            }
        }
    }
}
