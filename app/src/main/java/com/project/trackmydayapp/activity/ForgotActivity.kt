package com.project.trackmydayapp.activity

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import com.project.trackmydayapp.R
import com.project.trackmydayapp.activity.user.DashboardActivity
import com.project.trackmydayapp.database.DatabaseHandler
import com.project.trackmydayapp.helper.GMailSender
import java.lang.Exception
import java.util.*

class ForgotActivity : AppCompatActivity() {
    private lateinit var btn_forgot: TextView
    private lateinit var ed_forgot_email: EditText
    val db: DatabaseHandler = DatabaseHandler(this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)
        supportActionBar?.title = "Forgot Password"
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);

        btn_forgot = findViewById(R.id.btn_forgot);
        ed_forgot_email = findViewById(R.id.ed_forgot_email);
        btn_forgot.setOnClickListener {
            if (ed_forgot_email.text.toString().isEmpty()) {
                ed_forgot_email.setError("Enter Email")
            } else {
                val rnd = Random()
                val number: Int = rnd.nextInt(999999)
                db.updateForgot(number,ed_forgot_email.text.toString())
                sync(number,ed_forgot_email.text.toString()).execute()
                intent = Intent(this, OTPActivity::class.java)
                intent.putExtra("email",ed_forgot_email.text.toString())
                startActivity(intent)
                finish()

            }
        }
    }

    class sync(val rnds: Int,val email:String) : AsyncTask<String?, Void?, String?>() {
        override fun doInBackground(vararg p0: String?): String? {
            val sender = GMailSender("trackmydayapp@gmail.com", "Trackmyday@001")
            try {
                sender.sendMail(
                    "Forgot Mail From TrackMyDay",
                    "OTP Send "+rnds,
                    "trackmydayapp@gmail.com",
                    email
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.getItemId() === android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }

}