package com.example.famsaf

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class ActivityMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomBar = findViewById<BottomNavigationView>(R.id.BottomBarMenu)

        bottomBar.setOnItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                R.id.Guard -> {
                    inflateFragment(GuardFragment.newInstance())
                }
                R.id.home -> {
                    inflateFragment(HomeFragment.newInstance())
                }
                //
                R.id.dashboard -> {
                    inflateFragment(MapsFragment())
                }
                else -> {
                    inflateFragment(ProfileFragment.newInstance())
                }
            }
            true

        }
        bottomBar.selectedItemId = R.id.home
    }

    private fun inflateFragment(newInstance: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, newInstance)
        transaction.commit()
    }
}