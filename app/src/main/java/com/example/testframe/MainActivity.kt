package com.example.testframe

import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.testframe.ui.entertainment.EntertainmentFragment
import com.example.testframe.ui.market.MarketFragment
import com.example.testframe.ui.news.NewsFragment
import com.example.testframe.ui.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var selectedFragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(navListener)

        selectedFragment = NewsFragment()
        supportFragmentManager.beginTransaction().replace(
            R.id.nav_host_fragment,
            selectedFragment
        ).addToBackStack(null)
            .commit()

        initSharedPreferences()
    }

    private fun initSharedPreferences() {
        sharedPreferences = getSharedPreferences(0.toString(), MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val lng: Long = sharedPreferences.getLong("value", 0)
        editor.putLong("value", lng + 5000)
        editor.apply()
    }

    private val navListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_entertainment -> {
                    selectedFragment = EntertainmentFragment()
                }
                R.id.navigation_news -> {
                    selectedFragment = NewsFragment()
                }
                R.id.navigation_profile -> {
                    selectedFragment = ProfileFragment()
                }
                R.id.navigation_market -> {
                    val v:Long = 2
                    selectedFragment = MarketFragment()
                }
                else -> selectedFragment = ProfileFragment()
            }
            supportFragmentManager.beginTransaction().replace(
                R.id.nav_host_fragment,
                selectedFragment
            ).addToBackStack(null)
                .commit()

            true
        }

}