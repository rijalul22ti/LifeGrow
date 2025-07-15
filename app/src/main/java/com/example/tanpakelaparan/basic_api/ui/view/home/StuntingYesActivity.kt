package com.example.tanpakelaparan.basic_api.ui.view.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tanpakelaparan.R
import com.example.tanpakelaparan.databinding.ActivityStuntingYesBinding

class StuntingYesActivity : AppCompatActivity() {
    private var _binding: ActivityStuntingYesBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_stunting_yes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        _binding = ActivityStuntingYesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnKonten.setOnClickListener {

        }
    }
}