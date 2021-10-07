package com.project.trackmydayapp.activity.user

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.project.trackmydayapp.R
import com.project.trackmydayapp.database.DatabaseHandler
import java.util.regex.Pattern

class RegistrationActivity : AppCompatActivity() {
    lateinit var ed_reg_email: EditText;
    lateinit var ed_reg_pass: EditText;
    lateinit var ed_reg_repass: EditText;
    lateinit var btn_reg: TextView;
    val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    lateinit var txt_already_reg: TextView;


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        initUI();


        btn_reg.setOnClickListener(View.OnClickListener {
            if (ed_reg_email.text.isEmpty()) {
                ed_reg_email.setError("Enter Email")
            } else if (ed_reg_pass.text.isEmpty()) {
                ed_reg_pass.setError("Enter Password")
            } else if (ed_reg_repass.text.isEmpty()) {
                ed_reg_repass.setError("Enter Re-Password")
            } else if (ed_reg_pass.text.length < 6) {
                ed_reg_pass.setError("Enter atleast 6 length Password")
            } else if (!ed_reg_pass.text.toString().equals(ed_reg_repass.text.toString())) {
                Toast.makeText(this, "Password not match", Toast.LENGTH_SHORT).show()
            } else if (!checkEmail(ed_reg_email.text.toString())) {
                ed_reg_email.setError("Invalied Email")
            } else {

                val db: DatabaseHandler = DatabaseHandler(this);
                val status = db.addUser(ed_reg_email.text.toString(), ed_reg_pass.text.toString());
                if (status > -1) {
                    val userid =
                        db.login(ed_reg_email.text.toString(), ed_reg_pass.text.toString());
                    Toast.makeText(this, "Sign up Successfully", Toast.LENGTH_SHORT).show()
                    intent = Intent(this, ProfileUserActivity::class.java)
                    intent.putExtra("userId", userid)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                }

            }
        })
        txt_already_reg.setOnClickListener { onBackPressed() }
    }

    private fun checkEmail(email: String): Boolean {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
    }

    private fun initUI() {
        txt_already_reg = findViewById(R.id.txt_already_reg);


        ed_reg_email = findViewById(R.id.ed_reg_email);
        ed_reg_pass = findViewById(R.id.ed_reg_pass);
        ed_reg_repass = findViewById(R.id.ed_reg_repass);
        btn_reg = findViewById(R.id.btn_reg);
    }
}