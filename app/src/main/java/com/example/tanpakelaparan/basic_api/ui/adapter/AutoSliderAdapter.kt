package com.example.tanpakelaparan.basic_api.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.tanpakelaparan.R
import com.example.tanpakelaparan.basic_api.data.model.BeritaArtikelModel
import com.example.tanpakelaparan.basic_api.data.model.ItemModel
import com.example.tanpakelaparan.basic_api.ui.view.WebViewActivity
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AutoSliderAdapter(
    private val images: List<BeritaArtikelModel>,
    private val viewPager: ViewPager2
) : RecyclerView.Adapter<AutoSliderAdapter.SliderViewHolder>() {
    private var currentPosition = 0

    init {
        startAutoSlide()
    }

    class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val imageText: TextView = itemView.findViewById(R.id.imageText)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slide_item,parent, false)
        return SliderViewHolder(view)
    }
    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
//        holder.imageView.setImageResource(images[position])
        val itemUrl = images[position]
        Picasso.get().load(itemUrl.imageUrl).into(holder.imageView)
        holder.imageText.text = itemUrl.imageText
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("url", itemUrl.link) // Kirim URL ke WebViewActivity
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }
    private fun startAutoSlide(){
        CoroutineScope(Dispatchers.Main).launch {
            while (true){
                delay(3000)
                currentPosition = (currentPosition + 1) % itemCount
                viewPager.setCurrentItem(currentPosition, true)
            }
        }
    }

}