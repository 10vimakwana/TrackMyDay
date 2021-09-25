package com.project.trackmydayapp.activity.user

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.project.trackmydayapp.R
import com.project.trackmydayapp.activity.LoginActivity
import com.project.trackmydayapp.database.DatabaseHandler
import com.project.trackmydayapp.databinding.ActivityDashboardBinding
import com.project.trackmydayapp.helper.SessionManager
import com.project.trackmydayapp.model.UserProfileModel

class DashboardActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarDashboard.toolbar)

        binding.appBarDashboard.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val sessionManager: SessionManager = SessionManager.getInstance(this)!!
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        navView.menu.findItem(R.id.nav_logout).setOnMenuItemClickListener { menuItem ->
            sessionManager.setString("userId", "0")
            val a = Intent(this, LoginActivity::class.java)
            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(a)
            true
        }
        val navController = findNavController(R.id.nav_host_fragment_content_dashboard)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
              R.id.nav_dashboard,  R.id.nav_home, R.id.nav_all_user, R.id.nav_slideshow,R.id.nav_feedback
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        var profilelist: ArrayList<UserProfileModel> = ArrayList<UserProfileModel>()
        val db: DatabaseHandler = DatabaseHandler(this);
        profilelist = db.viewProfileUser(sessionManager.userId.toString().toInt());

        val headerView : View = navView.getHeaderView(0)
        val navUsername : TextView = headerView.findViewById(R.id.tv_dashboard_user)
        val navUserEmail : TextView = headerView.findViewById(R.id.tv_dashboard_email)

        navUsername.text = profilelist.get(0).firstname.toString()+"'s Dashboard"
        navUserEmail.text = profilelist.get(0).firstname.toString()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.dashboard, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_dashboard)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}