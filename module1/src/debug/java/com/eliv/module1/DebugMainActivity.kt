package com.eliv.module1

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

class DebugMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val btn = Button(this)
        btn.text = "打开 Module1 activity"
        setContentView(btn)
        btn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
