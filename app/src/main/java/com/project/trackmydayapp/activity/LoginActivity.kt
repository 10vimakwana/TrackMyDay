package com.project.trackmydayapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.project.trackmydayapp.R
import com.project.trackmydayapp.activity.admin.AdminActivity
import com.project.trackmydayapp.activity.user.DashboardActivity
import com.project.trackmydayapp.activity.user.ProfileUserActivity
import com.project.trackmydayapp.activity.user.RegistrationActivity
import com.project.trackmydayapp.database.DatabaseHandler
import com.project.trackmydayapp.helper.SessionManager

class LoginActivity : AppCompatActivity() {

    lateinit var txt_already_reg: TextView;
    lateinit var txt_forgot: TextView;
    lateinit var ed_login_email: EditText;
    lateinit var ed_login_pass: EditText;
    lateinit var btn_login: TextView;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initUI();
        val db: DatabaseHandler = DatabaseHandler(this);
        val sessionManager: SessionManager = SessionManager.getInstance(this)!!


        btn_login.setOnClickListener(View.OnClickListener {
            if (ed_login_email.text.isEmpty()) {
                ed_login_email.setError("Enter Email")
            } else if (ed_login_pass.text.isEmpty()) {
                ed_login_pass.setError("Enter Password")
            } else {
                val userid =
                    db.login(ed_login_email.text.toString(), ed_login_pass.text.toString());
                if (userid > 0) {
                    if (ed_login_email.text.toString().equals("admin@gmail.com")) {
                        sessionManager.setString("userId", "1");
                        intent = Intent(this, AdminActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        if (db.isProfile(userid)) {
                            sessionManager.setString("userId", userid.toString());
                            intent = Intent(this, DashboardActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            intent = Intent(this, ProfileUserActivity::class.java)
                            intent.putExtra("userId", userid);
                            sessionManager.setString("userId", userid.toString());

                            startActivity(intent)
                        }

                    }
                } else {
                    Toast.makeText(this, "not registration yet", Toast.LENGTH_SHORT).show()
                }
            }
        })


        txt_forgot.setOnClickListener(View.OnClickListener {
            intent = Intent(this, ForgotActivity::class.java)
            startActivity(intent)
        })
        txt_already_reg.setOnClickListener(View.OnClickListener {
            intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        })
    }

    private fun initUI() {
        txt_already_reg = findViewById(R.id.txt_already_reg);
        txt_forgot = findViewById(R.id.txt_forgot);
        btn_login = findViewById(R.id.btn_login);
        ed_login_email = findViewById(R.id.ed_login_email);
        ed_login_pass = findViewById(R.id.ed_login_pass);
    }
}