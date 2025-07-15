package com.example.tanpakelaparan.basic_api.ui.view.welcome_screen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.tanpakelaparan.basic_api.ui.view.Login
import com.example.tanpakelaparan.R
import com.google.firebase.auth.FirebaseAuth
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class WelcomeScreenActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var dotsIndicator: DotsIndicator
    private lateinit var btnSkip: Button
    private lateinit var btnContinue: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if (auth.currentUser != null) {
            finishWelcomeScreen()
            return
        }

        viewPager = findViewById(R.id.viewPager)
        dotsIndicator = findViewById(R.id.dot_indicator)

        val fragmentList = listOf(Welcome1Fragment(), Welcome2Fragment(), Welcome3Fragment())
        val adapter = PagerAdapter(this, fragmentList)
        viewPager.adapter = adapter

        dotsIndicator.attachTo(viewPager)

        btnSkip = findViewById(R.id.btnSkip)
        btnContinue = findViewById(R.id.btnContinue)

        btnContinue.setOnClickListener{
            if(viewPager.currentItem < fragmentList.size - 1){
                viewPager.currentItem+=1
            }else{
                finishWelcomeScreen()
            }
        }

        btnSkip.setOnClickListener{
            finishWelcomeScreen()
        }
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int){
                super.onPageSelected(position)
                if(position == fragmentList.size -1){
                    btnContinue.text="Finish"
                    btnSkip.visibility= View.GONE
                }else{
                    btnContinue.text="Next"
                    btnSkip.visibility=View.VISIBLE
                }
            }
        })

    }
    private fun finishWelcomeScreen(){
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }
}