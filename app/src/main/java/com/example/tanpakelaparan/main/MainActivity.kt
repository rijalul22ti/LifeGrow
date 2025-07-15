package com.example.tanpakelaparan.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.tanpakelaparan.R
import com.example.tanpakelaparan.basic_api.ui.view.home.HomeFragment
import com.example.tanpakelaparan.basic_api.ui.view.konten.KontenFragment
import com.example.tanpakelaparan.basic_api.ui.view.more.MoreFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
        loadFragment(HomeFragment())

        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNavView)

        bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    loadFragment(HomeFragment())
                }
                R.id.article -> {
                    loadFragment(KontenFragment())
                }
                R.id.more -> {
                    loadFragment(MoreFragment())
                }
            }
            true
        }
    }

    fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    fun stackFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun toActivity(activity: Activity){
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
    }

    fun setActiveBottomNav(itemId: Int) {
        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNavView)
        bottomNav.selectedItemId = itemId
    }
}