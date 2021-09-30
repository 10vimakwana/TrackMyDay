package com.project.trackmydayapp.activity.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.project.trackmydayapp.R
import com.project.trackmydayapp.database.DatabaseHandler
import com.project.trackmydayapp.helper.SessionManager

class ProfileUserActivity : AppCompatActivity() {
    lateinit var ed_reg_firstname: EditText;
    lateinit var ed_reg_height: EditText;
    lateinit var ed_reg_weight: EditText;
    lateinit var ed_reg_age: EditText;
    lateinit var btn_reg: TextView;
    lateinit var gender: RadioGroup;
    lateinit var rg_activity: RadioGroup;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_user)
        initUI();
        val userid = intent.getIntExtra("userId", 0);
        println("ID"+userid);
        btn_reg.setOnClickListener(View.OnClickListener {
            if (ed_reg_firstname.text.isEmpty()) {
                ed_reg_firstname.setError("Enter First Name")
            } else if (ed_reg_height.text.isEmpty()) {
                ed_reg_height.setError("Enter Height")
            } else if (ed_reg_weight.text.isEmpty()) {
                ed_reg_weight.setError("Enter Weight")
            } else if (ed_reg_age.text.isEmpty()) {
                ed_reg_age.setError("Enter Age")
            } else {
                val genderId = gender.checkedRadioButtonId
                val genderString = resources.getResourceEntryName(genderId).toString()
                val db: DatabaseHandler = DatabaseHandler(this);
                val actvityid = rg_activity.checkedRadioButtonId
                val activityString = resources.getResourceEntryName(actvityid).toString()
                val status = db.addProfileUser(userid,ed_reg_firstname.text.toString(), ed_reg_weight.text.toString().toDouble(),ed_reg_height.text.toString().toDouble(),ed_reg_age.text.toString(),genderString,activityString);
                if (status > -1) {
                    Toast.makeText(this, "Profile Saved", Toast.LENGTH_SHORT).show()
                    val sessionManager: SessionManager = SessionManager.getInstance(this)!!
                    sessionManager.setString("userId", userid.toString());
                    intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Something want wrong", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    private fun initUI() {

        ed_reg_firstname = findViewById(R.id.ed_reg_firstname);
        ed_reg_height = findViewById(R.id.ed_reg_height);
        ed_reg_weight = findViewById(R.id.ed_reg_weight);
        ed_reg_age = findViewById(R.id.ed_reg_age);
        gender = findViewById(R.id.rg_reg_gender);
        rg_activity = findViewById(R.id.rg_activity);
        btn_reg = findViewById(R.id.btn_profile_reg);

    }
}