package com.project.trackmydayapp.activity.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.project.trackmydayapp.R
import com.project.trackmydayapp.database.DatabaseHandler

class AddFoodActivity : AppCompatActivity() {
    lateinit var ed_food_name: EditText;
    lateinit var ed_common_name: EditText;
    lateinit var ed_description: EditText;
    lateinit var ed_nitrogen: EditText;
    lateinit var ed_fat_factor: EditText;
    lateinit var ed_specific_gravity: EditText;
    lateinit var ed_analysed_portion: EditText;
    lateinit var ed_unanalysed_portion: EditText;
    lateinit var ed_sampling_details: EditText;
    lateinit var btn_add_food: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food)

        supportActionBar?.title = "Add Food"
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);

        initUI()
        val db: DatabaseHandler = DatabaseHandler(this);

        btn_add_food.setOnClickListener(View.OnClickListener {
            if (ed_food_name.text.isEmpty()) {
                ed_food_name.setError("Enter Food Name")
            } else if (ed_common_name.text.isEmpty()) {
                ed_common_name.setError("Enter Common Name")
            } else if (ed_description.text.isEmpty()) {
                ed_description.setError("Enter Description")
            } else if (ed_nitrogen.text.isEmpty()) {
                ed_nitrogen.setError("Enter Nitrogen")
            } else if (ed_fat_factor.text.isEmpty()) {
                ed_fat_factor.setError("Enter Fat Factor")
            } else if (ed_specific_gravity.text.isEmpty()) {
                ed_specific_gravity.setError("Enter Specific Gravity")
            } else if (ed_analysed_portion.text.isEmpty()) {
                ed_analysed_portion.setError("Enter Analysed Portion")
            } else if (ed_unanalysed_portion.text.isEmpty()) {
                ed_unanalysed_portion.setError("Enter Unanalysed Portion")
            } else if (ed_sampling_details.text.isEmpty()) {
                ed_sampling_details.setError("Enter Sampling Details")
            } else {
                val foodid = db.addFood(
                    ed_food_name.text.toString(),
                    ed_common_name.text.toString(),
                    ed_description.text.toString(),
                    ed_nitrogen.text.toString().toDouble(),
                    ed_fat_factor.text.toString().toDouble(),
                    ed_specific_gravity.text.toString().toInt(),
                    ed_analysed_portion.text.toString().toInt(),
                    ed_unanalysed_portion.text.toString().toInt(),
                    ed_sampling_details.text.toString()
                )

                if (foodid > 0) {
                    Toast.makeText(this, "Successfully Added", Toast.LENGTH_SHORT).show()
                    onBackPressed()
                } else {
                    Toast.makeText(this, "issue of adding food", Toast.LENGTH_SHORT).show()
                }
            }

        })

    }

    private fun initUI() {

        ed_food_name = findViewById(R.id.ed_food_name);
        ed_common_name = findViewById(R.id.ed_common_name);
        ed_description = findViewById(R.id.ed_description);
        ed_nitrogen = findViewById(R.id.ed_nitrogen);
        ed_fat_factor = findViewById(R.id.ed_fat_factor);
        ed_specific_gravity = findViewById(R.id.ed_specific_gravity);
        ed_analysed_portion = findViewById(R.id.ed_analysed_portion);
        ed_unanalysed_portion = findViewById(R.id.ed_unanalysed_portion);
        ed_sampling_details = findViewById(R.id.ed_sampling_details);
        btn_add_food = findViewById(R.id.btn_add_food);

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.getItemId() === android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }
}