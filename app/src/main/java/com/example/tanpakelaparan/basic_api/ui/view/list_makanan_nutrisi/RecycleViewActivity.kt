package com.example.tanpakelaparan.basic_api.ui.view.list_makanan_nutrisi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tanpakelaparan.R
import com.example.tanpakelaparan.basic_api.data.model.ItemModel
import com.example.tanpakelaparan.basic_api.ui.adapter.ItemAdapter

class RecycleViewActivity : AppCompatActivity() {
    lateinit var adapter: ItemAdapter
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycle_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val items = listOf(
            ItemModel(
                "https://cdn1-production-images-kly.akamaized.net/NjFnTUug6DhB-tsRlkSZ_THjN4o=/640x360/smart/filters:quality(75):strip_icc():format(webp)/kly-media-production/medias/3390091/original/047746000_1614610130-Croatian_Salad.jpg",
                "Salad"
            ),
            ItemModel(
                "https://bifid.com.tr/wp-content/uploads/2023/05/Avokado-Hasat-Zamani-Bifid.webp",
                "Buah alpukat"
            ),
            ItemModel(
                "https://awsimages.detik.net.id/community/media/visual/2021/01/30/keju.jpeg?w=700&q=90",
                "Keju"
            ),
            ItemModel(
                "https://res.cloudinary.com/dk0z4ums3/image/upload/v1711094324/attached_image/panduan-memberikan-yogurt-untuk-bayi-yang-perlu-diketahui-0-alodokter.jpg",
                "Yogurt"
            ),
            ItemModel(
                "https://res.cloudinary.com/dk0z4ums3/image/upload/v1592885785/attached_image/inilah-manfaat-telur-dan-cara-menyimpannya-0-alodokter.jpg",
                "Telur"
            ),
            ItemModel(
                "https://asset.kompas.com/crops/kqlFbv22qqQift2MxVOs2LvmPR8=/0x0:3888x2592/1200x800/data/photo/2023/02/22/63f5d0c6708d1.jpg",
                "Ikan berlemak"
            ),
            ItemModel(
                "https://cdn.hellosehat.com/wp-content/uploads/2018/07/Jenis-Kacang-kacangan-yang-Paling-Sehat-Dimakan.jpg?w=1200&q=75",
                "Kacang-kacangan"
            ),
        )

        adapter = ItemAdapter(items)
        recyclerView.adapter = adapter
    }
}