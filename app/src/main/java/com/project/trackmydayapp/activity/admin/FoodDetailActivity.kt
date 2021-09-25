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
import com.project.trackmydayapp.model.FoodModel

class FoodDetailActivity : AppCompatActivity() {
    lateinit var txt_dt_foodname: TextView;
    lateinit var txt_dt_commonname: TextView;
    lateinit var txt_dt_dec: TextView;
    lateinit var txt_dt_nitrogen: TextView;
    lateinit var txt_dt_fat_factor: TextView;
    lateinit var txt_dt_specificgravity: TextView;
    lateinit var txt_dt_analysed: TextView;
    lateinit var txt_dt_unanalysed: TextView;
    lateinit var txt_dt_samplingdetails: TextView;
    val db: DatabaseHandler = DatabaseHandler(this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_detail)
        supportActionBar?.title = "Food Detail"
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        val foodid = intent.getIntExtra("foodid", 0);

        initUI()

        var foodlist: ArrayList<FoodModel> = ArrayList<FoodModel>()

        foodlist = db.viewFood(foodid);
        txt_dt_foodname.text = foodlist.get(0).foodName
        txt_dt_commonname.text = foodlist.get(0).commonName
        txt_dt_dec.text = foodlist.get(0).description
        txt_dt_nitrogen.text = foodlist.get(0).nitrogen.toString()
        txt_dt_fat_factor.text = foodlist.get(0).fat.toString()
        txt_dt_specificgravity.text = foodlist.get(0).specificGravity.toString()
        txt_dt_analysed.text = foodlist.get(0).analysedPortion.toString()
        txt_dt_unanalysed.text = foodlist.get(0).unanalyPortion.toString()
        txt_dt_samplingdetails.text = foodlist.get(0).samplingDetails.toString()
    }

    private fun initUI() {
        txt_dt_foodname = findViewById(R.id.txt_dt_foodname);
        txt_dt_commonname = findViewById(R.id.txt_dt_commonname);
        txt_dt_dec = findViewById(R.id.txt_dt_dec);
        txt_dt_nitrogen = findViewById(R.id.txt_dt_nitrogen);
        txt_dt_fat_factor = findViewById(R.id.txt_dt_fat_factor);
        txt_dt_specificgravity = findViewById(R.id.txt_dt_specificgravity);
        txt_dt_analysed = findViewById(R.id.txt_dt_analysed);
        txt_dt_unanalysed = findViewById(R.id.txt_dt_unanalysed);
        txt_dt_samplingdetails = findViewById(R.id.txt_dt_samplingdetails);
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.food_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.getItemId() === android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }else if (item.itemId == R.id.ic_menu_delete){
            val foodid = intent.getIntExtra("foodid", 0);
            db.deleteFood(foodid)
            Toast.makeText(this, "Food Delete Successfuly", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }else if (item.itemId == R.id.ic_menu_edit){
            val foodid = intent.getIntExtra("foodid", 0);
            startActivity(Intent(this, FoodEditActivity::class.java).putExtra("foodid",foodid))
            finish()

        }
        return super.onOptionsItemSelected(item)
    }
}