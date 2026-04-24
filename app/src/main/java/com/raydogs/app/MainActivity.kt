package com.raydogs.app

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.raydogs.app.adapter.CategoryPagerAdapter
import com.raydogs.app.worker.NewPostWorker
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val requestNotificationPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { /* no-op */ }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(true)

        val tabs = listOf(
            Tab("Home",     null),
            Tab("Breeds",   41),
            Tab("Training", 1),
            Tab("Health",   39),
            Tab("Reviews",  42)
        )

        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)

        viewPager.adapter = CategoryPagerAdapter(this, tabs)
        viewPager.offscreenPageLimit = tabs.size

        TabLayoutMediator(tabLayout, viewPager) { tab, pos ->
            tab.text = tabs[pos].label
        }.attach()

        scheduleNewPostChecks()
        askNotificationPermission()
    }

    private fun scheduleNewPostChecks() {
        val request = PeriodicWorkRequestBuilder<NewPostWorker>(1, TimeUnit.HOURS).build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            NewPostWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
    }

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestNotificationPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    data class Tab(val label: String, val categoryId: Int?)
}
