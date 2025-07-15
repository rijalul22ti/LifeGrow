package com.example.tanpakelaparan.basic_api.ui.view.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.tanpakelaparan.R
import com.example.tanpakelaparan.basic_api.data.model.RiwayatModel
import com.example.tanpakelaparan.basic_api.ui.adapter.RiwayatAdapter
import com.example.tanpakelaparan.databinding.ActivityPrediksiStuntingBinding
import com.example.tanpakelaparan.databinding.PrediksiStuntingInputBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.firestore.FirebaseFirestore
import org.json.JSONException
import org.json.JSONObject
import java.nio.charset.Charset

class PrediksiStuntingActivity : AppCompatActivity() {
    private var _binding: ActivityPrediksiStuntingBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: RiwayatAdapter
    private val riwayatList = mutableListOf<RiwayatModel>()

    private val firestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    private val url = "http://10.0.2.2:5000/predict"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityPrediksiStuntingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.fab.setOnClickListener {
            showBottomSheetDialog()
        }

        getRiwayat()

        adapter = RiwayatAdapter(riwayatList)
        binding.productList.adapter = adapter
        binding.productList.layoutManager = LinearLayoutManager(this)
    }

    private fun showBottomSheetDialog() {
        val bindingBottom =
            PrediksiStuntingInputBinding.inflate(layoutInflater)
        val bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(bindingBottom.root)

        bindingBottom.btnRegister.setOnClickListener {
            val jenisKelamin: Int = if (bindingBottom.radioPirates.isChecked) {
                1
            } else {
                0
            }
            val umur = bindingBottom.inputUmur.text.toString().toInt()
            val beratLahir = bindingBottom.inputBeratLahir.text.toString().toDouble()
            val panjangLahir = bindingBottom.inputPanjangLahir.text.toString().toDouble()
            val beratSekarang = bindingBottom.inputBeratSekarang.text.toString().toDouble()
            val tinggiSekarang = bindingBottom.inputTinggiSekarang.text.toString().toDouble()

            if (jenisKelamin == null || beratLahir.isNaN() || panjangLahir.isNaN() ||
                beratSekarang.isNaN() || tinggiSekarang.isNaN()
            ) {
                Toast.makeText(this, "Semua data harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val jsonObject = JSONObject()
            jsonObject.put("gender_male", jenisKelamin)
            jsonObject.put("age", umur)
            jsonObject.put("birth_weight", beratLahir)
            jsonObject.put("birth_length", panjangLahir)
            jsonObject.put("body_weight", beratSekarang)
            jsonObject.put("body_length", tinggiSekarang)
            val requestBody = jsonObject.toString()
            val jsonObjectRequest = object : JsonObjectRequest(
                Method.POST, url, jsonObject,
                Response.Listener { response ->
                    try {
                        val data = response.getLong("stunting").toInt()
                        val prediksi = data
                        if (prediksi == 1) {
                            startActivity(Intent(this, StuntingYesActivity::class.java))
                        } else {
                            startActivity(Intent(this, StuntingNoActivity::class.java))
                        }
                        handleFormData(jenisKelamin,
                            umur,
                            beratLahir,
                            panjangLahir,
                            beratSekarang,
                            tinggiSekarang,
                            prediksi)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { error ->
                    Toast.makeText(this@PrediksiStuntingActivity, error.message, Toast.LENGTH_SHORT)
                        .show()
                }) {
                override fun getBodyContentType(): String {
                    return "application/json"
                }

                override fun getBody(): ByteArray {
                    return requestBody.toByteArray(Charset.defaultCharset())
                }
            }
            val queue = Volley.newRequestQueue(this@PrediksiStuntingActivity)
            queue.add(jsonObjectRequest)

            bottomSheetDialog.dismiss()

        }

        bottomSheetDialog.show()
    }

    private fun handleFormData(
        jenisKelamin: Int,
        umur: Int,
        beratLahir: Double,
        panjangLahir: Double,
        beratSekarang: Double,
        tinggiSekarang: Double,
        stunting: Int
    ) {
        val data = hashMapOf(
            "gender_male" to jenisKelamin,
            "age" to umur,
            "birth_weight" to beratLahir,
            "birth_length" to panjangLahir,
            "body_weight" to beratSekarang,
            "body_length" to tinggiSekarang,
            "stunting" to stunting,
            "timestamp" to System.currentTimeMillis()
        )

        // Simpan data ke Firestore di koleksi "prediksiStunting"
        firestore.collection("prediksiStunting")
            .add(data)
            .addOnSuccessListener {
                Toast.makeText(this, "Data berhasil disimpan!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(
                    this,
                    "Gagal menyimpan data: ${exception.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
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

                    riwayatList.add(
                        RiwayatModel(
                            id,
                            jenisKelamin,
                            umur,
                            beratLahir,
                            panjangLahir,
                            beratSekarang,
                            tinggiSekarang,
                            stunting
                        )
                    )
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