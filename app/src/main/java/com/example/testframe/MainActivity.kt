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
    var sharedPreferences: SharedPreferences? = null
    lateinit var selectedFragment: Fragment

    lateinit var active: Fragment//1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(navListener);
        sharedPreferences = getSharedPreferences(0.toString(), MODE_PRIVATE)
        selectedFragment = NewsFragment()
        supportFragmentManager.beginTransaction().replace(
            R.id.nav_host_fragment,
            selectedFragment
        ).addToBackStack(null)
            .commit()
        val editor = sharedPreferences!!.edit()
        var lng:Long = sharedPreferences!!.getLong("value",0)
        editor.putLong("value", lng+5000)
        editor.apply()

    }

    private val navListener: BottomNavigationView.OnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.getItemId()) {
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
                        var v:Long = 2
                        selectedFragment = MarketFragment(sharedPreferences!!.getLong("value",v))
                    }
                    else -> selectedFragment = ProfileFragment()
                }
                supportFragmentManager.beginTransaction().replace(
                    R.id.nav_host_fragment,
                    selectedFragment
                ).addToBackStack(null)
                    .commit()

                return true
            }
        }

}