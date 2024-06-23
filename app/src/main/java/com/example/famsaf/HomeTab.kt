package com.example.famsaf

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tipcalculator.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeTab : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_tab)

        val bottomBar = findViewById<BottomNavigationView>(R.id.BottomBarMenu)

        bottomBar.setOnItemSelectedListener { menuItem ->

            if(menuItem.itemId == R.id.Guard){
                inflateFragment()
            }
            else if(menuItem.itemId == R.id.home){
                inflateHomeFragment()
            }
            true
        }
    }

    private fun inflateHomeFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, GuardFragment.newInstance())
        transaction.commit()
    }

    private fun inflateFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, HomeFragment.newInstance())
        transaction.commit()
    }
}