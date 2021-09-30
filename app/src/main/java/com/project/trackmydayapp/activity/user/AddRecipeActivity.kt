package com.project.trackmydayapp.activity.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Spinner
import com.project.trackmydayapp.R
import com.project.trackmydayapp.adapter.RecipeArrayAdapter
import com.project.trackmydayapp.database.DatabaseHandler
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import com.project.trackmydayapp.helper.SessionManager


class AddRecipeActivity : AppCompatActivity() {
    private lateinit var sp_recipe: Spinner
    private lateinit var btn_add_recipes: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)
        supportActionBar?.title = "Add Recipe"
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);

        initUI();
        val date = intent.getStringExtra("date");


        val db: DatabaseHandler = DatabaseHandler(this);

        sp_recipe.adapter = RecipeArrayAdapter(this, db.viewFood());
        val sessionManager: SessionManager = SessionManager.getInstance(this)!!


        btn_add_recipes.setOnClickListener {

            val status = db.addRecipe(
                sessionManager.userId.toString().toInt(),
                db.viewFood().get(sp_recipe.selectedItemPosition).foodId,
                db.viewFood().get(sp_recipe.selectedItemPosition).calories,
                db.viewFood().get(sp_recipe.selectedItemPosition).foodName,
                date.toString()
            );
            if (status > -1) {
                Toast.makeText(applicationContext, "Recipe Successfully Added", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }

    private fun initUI() {

        sp_recipe = findViewById(R.id.sp_recipe);
        btn_add_recipes = findViewById(R.id.btn_add_recipes);

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.getItemId() === android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }
}