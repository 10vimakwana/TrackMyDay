package com.project.trackmydayapp.activity.admin

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.project.trackmydayapp.activity.LoginActivity
import com.project.trackmydayapp.databinding.ActivityAdminBinding
import com.project.trackmydayapp.helper.SessionManager
import androidx.core.view.MenuItemCompat

import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import com.project.trackmydayapp.R
import com.project.trackmydayapp.activity.admin.fragment.FoodFragment


class AdminActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarAdmin.toolbar)

        binding.appBarAdmin.fab.setOnClickListener { view ->
            //         Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //  .setAction("Action", null).show()
            intent = Intent(this, AddFoodActivity::class.java)
            startActivity(intent)
        }
        val sessionManager: SessionManager = SessionManager.getInstance(this)!!
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        navView.menu.findItem(R.id.nav_logout).setOnMenuItemClickListener { menuItem ->
            sessionManager.setString("userId", "0")
            val a = Intent(this, LoginActivity::class.java)
            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(a)
            finish()
            true
        }
        val navController = findNavController(R.id.nav_host_fragment_content_admin)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
             R.id.nav_dashboard,   R.id.nav_home, R.id.nav_all_user, R.id.nav_slideshow,R.id.nav_feedback
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

   /* override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.admin, menu)
        val searchViewItem: MenuItem = menu.findItem(R.id.app_bar_search)
        val searchView: SearchView = MenuItemCompat.getActionView(searchViewItem) as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                *//*   if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(MainActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }*//*return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val fragment = FoodFragment()
                (fragment as FoodFragment).doSearch(newText)
                return false
            }
        })
        return true
    }*/

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_admin)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}