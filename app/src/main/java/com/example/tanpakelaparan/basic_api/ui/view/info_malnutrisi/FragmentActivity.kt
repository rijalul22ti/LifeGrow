package com.example.tanpakelaparan.basic_api.ui.view.info_malnutrisi

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.tanpakelaparan.R

class FragmentActivity : AppCompatActivity() {
    private lateinit var btnFragment1: Button
    private lateinit var btnFragment2: Button
    private lateinit var btnFragment3: Button
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fragment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, FirstFragment())
            .commit()
        btnFragment1 = findViewById(R.id.btnFragment1)
        btnFragment2 = findViewById(R.id.btnFragment2)
        btnFragment3 = findViewById(R.id.btnFragment3)

        toolbar = findViewById(R.id.toolbar)
        toolbar.setTitle("Fragment")
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        btnFragment1.setOnClickListener{
            replaceFragment(FirstFragment())
        }
        btnFragment2.setOnClickListener{
            replaceFragment(SecondFragment())
        }
        btnFragment3.setOnClickListener{
            replaceFragment(ThirdFragment())
        }
    }
    fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}