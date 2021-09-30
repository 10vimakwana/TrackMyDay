package com.project.trackmydayapp.activity.user

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.project.trackmydayapp.R
import com.project.trackmydayapp.adapter.SectionsPagerAdapter

class ViewDataActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var mViewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_data)
        supportActionBar?.title = "Details"
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);

        initUI()
        setupViewPager()
    }

    private fun initUI() {
        tabLayout = findViewById<View>(R.id.tablayout) as TabLayout
        mViewPager = findViewById<View>(R.id.container) as ViewPager
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.getItemId() === android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }
    private fun setupViewPager() {
        val date = intent.getStringExtra("date");

        var sectionsPagerAdapter =
            SectionsPagerAdapter(
                this,
                supportFragmentManager,
                date
            )
        mViewPager.setAdapter(sectionsPagerAdapter)
        tabLayout.setTabTextColors(Color.parseColor("#757575"), Color.parseColor("#FFFFFF"))
        tabLayout.setupWithViewPager(mViewPager)
        mViewPager.setOffscreenPageLimit(3)
        mViewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {}
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }


}