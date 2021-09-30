package com.project.trackmydayapp.activity.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.project.trackmydayapp.R
import com.project.trackmydayapp.database.DatabaseHandler
import com.project.trackmydayapp.helper.SessionManager

class AddStepActivity : AppCompatActivity() {
    private lateinit var ed_steps: EditText
    private lateinit var btn_add_step: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_step)
        supportActionBar?.title = "Add Step"
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        initUI();
        val date = intent.getStringExtra("date");

        btn_add_step.setOnClickListener {
            if (ed_steps.text.toString().isEmpty()) {
                ed_steps.setError("Enter Steps")
            } else {
                val db: DatabaseHandler = DatabaseHandler(this);
                val sessionManager: SessionManager = SessionManager.getInstance(this)!!

                val status = db.addStep(
                    sessionManager.userId.toString().toInt(),
                    ed_steps.text.toString().toInt(),
                    date.toString()
                );
                if (status > -1) {
                    onBackPressed()
                    Toast.makeText(this, "Steps Successfully Added", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun initUI() {

        ed_steps = findViewById(R.id.ed_steps);
        btn_add_step = findViewById(R.id.btn_add_step);

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.getItemId() === android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }
}