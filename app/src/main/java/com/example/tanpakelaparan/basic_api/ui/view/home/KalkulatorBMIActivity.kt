package com.example.tanpakelaparan.basic_api.ui.view.home

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tanpakelaparan.R

class KalkulatorBMIActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_kalkulator_bmiactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
// Menangkap referensi dari input dan tombol
        val beratBadanInput = findViewById<EditText>(R.id.beratBadan)
        val tinggiBadanInput = findViewById<EditText>(R.id.tinggiBadan)
        val hitungBmiButton = findViewById<Button>(R.id.btnHitungBMI)

        // Set listener untuk tombol hitung BMI
        hitungBmiButton.setOnClickListener {
            val beratBadan = beratBadanInput.text.toString().toDoubleOrNull()
            val tinggiBadan = tinggiBadanInput.text.toString().toDoubleOrNull()

            // Validasi input
            if (beratBadan == null || tinggiBadan == null || beratBadan <= 0 || tinggiBadan <= 0) {
                Toast.makeText(this, "Input tidak valid. Pastikan data dimasukkan dengan benar.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Kalkulasi BMI
            val bmi = beratBadan / (tinggiBadan * tinggiBadan)

            // Tampilkan hasil dalam AlertDialog
            showBmiResultDialog(bmi)
        }
    }

    // Fungsi untuk menampilkan AlertDialog dengan hasil BMI
    private fun showBmiResultDialog(bmi: Double) {
        val bmiCategory = when {
            bmi < 18.5 -> "Kurus"
            bmi in 18.5..24.9 -> "Normal"
            bmi in 25.0..29.9 -> "Kelebihan Berat Badan"
            else -> "Obesitas"
        }

        val message = "BMI: %.2f\nKategori: $bmiCategory".format(bmi)

        // Membuat dan menampilkan AlertDialog
        AlertDialog.Builder(this)
            .setTitle("Hasil BMI")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}