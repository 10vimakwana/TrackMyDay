package com.project.trackmydayapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.project.trackmydayapp.R
import com.project.trackmydayapp.database.DatabaseHandler

class ResetActivity : AppCompatActivity() {
    lateinit var ed_pass: EditText;
    lateinit var ed_repass: EditText;
    lateinit var btn_reset: TextView;
    val db: DatabaseHandler = DatabaseHandler(this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset)
        supportActionBar?.title = "Reset Password "
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        val email = intent.getStringExtra("email");
        val userid = intent.getIntExtra("userId", 0);

        ed_pass = findViewById(R.id.ed_reset_pass);
        ed_repass = findViewById(R.id.ed_reset_repass);
        btn_reset = findViewById(R.id.btn_reset);

        btn_reset.setOnClickListener {
            if (ed_pass.text.isEmpty()) {
                ed_pass.setError("Enter Password")
            } else if (ed_repass.text.isEmpty()) {
                ed_repass.setError("Enter Re-Password")
            } else if (!ed_pass.text.toString().equals(ed_repass.text.toString())) {
                Toast.makeText(this, "Password not match", Toast.LENGTH_SHORT).show()
            } else {
                val suucess =    db.updatePassword(userid, email.toString(), ed_pass.text.toString())
                if (suucess>0) {
                    onBackPressed()
                }else{
                    Toast.makeText(this, "Somthing went wrong", Toast.LENGTH_SHORT).show()

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