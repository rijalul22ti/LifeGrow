package com.example.tanpakelaparan.basic_api.ui.view.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tanpakelaparan.R
import com.example.tanpakelaparan.basic_api.data.model.RiwayatModel
import com.example.tanpakelaparan.basic_api.ui.adapter.RiwayatAdapter
import com.example.tanpakelaparan.databinding.ActivityRiwayatPrediksiBinding
import com.google.firebase.firestore.FirebaseFirestore

class RiwayatPrediksiActivity : AppCompatActivity() {
    private var _binding: ActivityRiwayatPrediksiBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: RiwayatAdapter
    private val riwayatList = mutableListOf<RiwayatModel>()

    private val firestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityRiwayatPrediksiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getRiwayat()

        adapter = RiwayatAdapter(riwayatList)
        binding.productList.adapter = adapter
        binding.productList.layoutManager = LinearLayoutManager(this)
    }
    fun getRiwayat() {

        firestore.collection("prediksiStunting")
            .get()
            .addOnSuccessListener { result ->
                riwayatList.clear()
                for (document in result) {
                    // Dapatkan data dari setiap dokumen
                    val id = document.getString("id")
                    val jenisKelamin = document.getLong("gender_male")?.toInt()
                    val umur = document.getLong("age")?.toInt()
                    val beratLahir = document.getDouble("birth_weight")
                    val panjangLahir = document.getDouble("birth_length")
                    val beratSekarang = document.getDouble("body_weight")
                    val tinggiSekarang = document.getDouble("body_length")
                    val stunting = document.getLong("stunting")?.toInt()

                    riwayatList.add(RiwayatModel(id, jenisKelamin, umur, beratLahir, panjangLahir, beratSekarang, tinggiSekarang, stunting))
                    println("halo ${jenisKelamin}")
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                // Tangani error
                println("Error getting documents: ${exception.message}")
            }

    }
}