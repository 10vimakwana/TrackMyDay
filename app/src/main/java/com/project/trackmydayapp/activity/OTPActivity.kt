package com.project.trackmydayapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.project.trackmydayapp.R
import com.project.trackmydayapp.activity.user.ProfileUserActivity
import com.project.trackmydayapp.database.DatabaseHandler

class OTPActivity : AppCompatActivity() {
    private lateinit var btn_otp: TextView
    private lateinit var ed_otp: EditText
    val db: DatabaseHandler = DatabaseHandler(this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpactivity)
        supportActionBar?.title = "OTP "
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        val email = intent.getStringExtra("email");

        btn_otp = findViewById(R.id.btn_otp);
        ed_otp = findViewById(R.id.ed_otp);

        btn_otp.setOnClickListener {
            if (ed_otp.text.toString().isEmpty()) {
                ed_otp.setError("Enter OTP")
            } else {
                val userid = db.otpmatch(email.toString(),ed_otp.text.toString().toInt())
                if (userid>0){
                    intent = Intent(this, ResetActivity::class.java)
                    intent.putExtra("userId",userid)
                    intent.putExtra("email",email)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this, "OTP Not Match", Toast.LENGTH_SHORT).show()
                }

            }
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