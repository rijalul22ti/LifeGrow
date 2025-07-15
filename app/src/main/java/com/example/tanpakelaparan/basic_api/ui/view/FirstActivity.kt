package com.example.tanpakelaparan.basic_api.ui.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tanpakelaparan.R
import com.example.tanpakelaparan.basic_api.ui.view.info_malnutrisi.FragmentActivity
import com.example.tanpakelaparan.basic_api.ui.view.list_makanan_nutrisi.RecycleViewActivity
import com.example.tanpakelaparan.basic_api.ui.view.menu_utama.ListViewActivity
import com.google.android.material.snackbar.Snackbar

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_first)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var btnWebView: Button = findViewById(R.id.btnWebView)
        var btnLogout: Button = findViewById(R.id.btnLogout)
        val btnFragment: Button = findViewById(R.id.btnFragment)

        val textWelcome: TextView = findViewById(R.id.textWelcome)
//        val username = intent.getStringExtra("username")

        btnWebView.setOnClickListener {
            val i = Intent(this, WebViewActivity::class.java)
//            i.putExtra("username", "Odi")

            startActivity(i)
        }

        val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", null)

        btnLogout.setOnClickListener {
            val editor = sharedPref.edit()
            editor.clear()
            editor.apply()

            val intent = Intent(this, Login::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        btnFragment.setOnClickListener {
            val i = Intent(this, FragmentActivity::class.java)
//            i.putExtra("username", "Odi")

            startActivity(i)
        }

        var btnListView: Button = findViewById(R.id.btnListView)

        btnListView.setOnClickListener{
            val i = Intent(this, ListViewActivity::class.java)
            startActivity(i)
        }

        var btnRecycleView: Button = findViewById(R.id.btnRecyclerView)

        btnRecycleView.setOnClickListener{
            val i = Intent(this, RecycleViewActivity::class.java)
            startActivity(i)
        }

        textWelcome.text = "Selamat datang, " + username
    }

    private fun showSnackBar(message: String) {
        val view = this.findViewById<View>(android.R.id.content)
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        snackbar.setAction("Aksi") {
            Toast.makeText(this, "Tombol aksi", Toast.LENGTH_LONG).show()
        }
        snackbar.show()
    }
}