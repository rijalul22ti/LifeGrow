package com.example.tanpakelaparan.basic_api.ui.view.menu_utama

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tanpakelaparan.basic_api.ui.view.FirstActivity
import com.example.tanpakelaparan.R
import com.example.tanpakelaparan.basic_api.data.model.ListModel
import com.example.tanpakelaparan.basic_api.ui.adapter.ListAdapter

class ListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val listView: ListView = findViewById(R.id.listView)

        val menuList = listOf(
            ListModel("Profil", "Profil", R.drawable.ic_profile),
            ListModel("Beranda", "Beranda", R.drawable.ic_home),
            ListModel("Kontak", "Kontak", R.drawable.ic_person),
            ListModel("Setelan", "Setelan", R.drawable.ic_setting),
        )

        val adapter = ListAdapter(this, menuList)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = menuList[position]

            if (selectedItem.name == "Profil") {
//                Toast.makeText(
//                    this,
//                    "Redirect ke halaman detail ${selectedItem.desc}",
//                    Toast.LENGTH_LONG
//                ).show()

                val i = Intent(this, ProfileActivity::class.java)
                startActivity(i)
            } else if (selectedItem.name == "Beranda") {
                val i = Intent(this, FirstActivity::class.java)
                startActivity(i)
            } else {
                Toast.makeText(
                    this,
                    "${selectedItem.desc} masih dalam pengembangan",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}