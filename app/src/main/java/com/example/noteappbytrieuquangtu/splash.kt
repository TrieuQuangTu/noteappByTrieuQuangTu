package com.example.noteappbytrieuquangtu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide() //hideActionBar
        Handler(Looper.getMainLooper()).postDelayed(Runnable {

            startActivity(Intent(this, MainActivity::class.java))

        }, 2000)
    }
}