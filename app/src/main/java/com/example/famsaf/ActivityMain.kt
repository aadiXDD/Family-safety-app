package com.example.famsaf

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class ActivityMain : AppCompatActivity() {

    val permissions = arrayOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.READ_CONTACTS
    )
    val permissionCode = 69

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        askForPermission()

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

    private fun askForPermission() {
        ActivityCompat.requestPermissions(this, permissions, permissionCode)
    }

    private fun inflateFragment(newInstance: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, newInstance)
        transaction.commit()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == permissionCode){
            if(allpermissionGranted()){

            }
            else{
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

//    private fun OpenCamera() {
//        val intent = Intent("android.media.action.IMAGE_CAPTURE")
//        startActivity(intent)
//    }

    private fun allpermissionGranted(): Boolean {
        for(items in permissions){
            if(ContextCompat.checkSelfPermission(this, items) != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }
}