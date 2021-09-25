package com.project.trackmydayapp.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.project.trackmydayapp.R
import com.project.trackmydayapp.activity.admin.AdminActivity
import com.project.trackmydayapp.activity.user.DashboardActivity
import com.project.trackmydayapp.helper.SessionManager


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(Runnable {
            val sessionManager: SessionManager = SessionManager.getInstance(this)!!
            if (sessionManager.userId != "0") {
                if (sessionManager.userId == "1") {
                    val mainIntent = Intent(this, AdminActivity::class.java)
                    startActivity(mainIntent)
                    finish()
                }else{
                    val mainIntent = Intent(this, DashboardActivity::class.java)
                    startActivity(mainIntent)
                    finish()
                }
            }else{
                val mainIntent = Intent(this, LoginActivity::class.java)
                startActivity(mainIntent)
                finish()
            }
        }, 3000)
    }
}