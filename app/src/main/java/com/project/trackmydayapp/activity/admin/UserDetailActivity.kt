package com.project.trackmydayapp.activity.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.project.trackmydayapp.R
import com.project.trackmydayapp.database.DatabaseHandler
import com.project.trackmydayapp.model.UserProfileModel

class UserDetailActivity : AppCompatActivity() {
    lateinit var txt_user_dt_name: TextView;
    lateinit var txt_user_dt_email: TextView;
    lateinit var txt_user_dt_height: TextView;
    lateinit var txt_user_dt_weight: TextView;
    lateinit var txt_user_dt_age: TextView;
    val db: DatabaseHandler = DatabaseHandler(this);
    var profilelist: ArrayList<UserProfileModel> = ArrayList<UserProfileModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        supportActionBar?.title = "User Detail"
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        val userid = intent.getIntExtra("userid", 0);
        val email = intent.getStringExtra("emailid");

        initUI()
        profilelist = db.viewProfileUser(userid)


        txt_user_dt_email.text = email
        if (profilelist.size > 0) {
            txt_user_dt_height.text = profilelist.get(0).height.toString()
            txt_user_dt_weight.text = profilelist.get(0).weight.toString()
            txt_user_dt_age.text = profilelist.get(0).age.toString()
            txt_user_dt_name.text = profilelist.get(0).firstname.toString()
        }

    }

    private fun initUI() {
        txt_user_dt_name = findViewById(R.id.txt_user_dt_name);
        txt_user_dt_email = findViewById(R.id.txt_user_dt_email);
        txt_user_dt_height = findViewById(R.id.txt_user_dt_height);
        txt_user_dt_weight = findViewById(R.id.txt_user_dt_weight);
        txt_user_dt_age = findViewById(R.id.txt_user_dt_age);
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.user, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.getItemId() === android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        } else if (item.itemId == R.id.action_user_delete) {
            val userid = intent.getIntExtra("userid", 0);
            db.deleteProfileUser(profilelist.get(0))
            db.deleteUser(userid)
            profilelist.removeAt(0)
            Toast.makeText(this, "User Deleted Successfully", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}