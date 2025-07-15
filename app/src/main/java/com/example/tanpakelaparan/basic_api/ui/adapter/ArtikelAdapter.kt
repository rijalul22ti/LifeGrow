package com.example.tanpakelaparan.basic_api.ui.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tanpakelaparan.R
import com.example.tanpakelaparan.basic_api.data.model.BeritaArtikelModel
import com.example.tanpakelaparan.basic_api.ui.view.WebViewActivity
import com.squareup.picasso.Picasso

class ArtikelAdapter(
    private var mList: List<BeritaArtikelModel>,
) : RecyclerView.Adapter<ArtikelAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_news_horizontal_item, parent, false)
        return ViewHolder(view)
    }

    fun updateDataSet(newItems: List<BeritaArtikelModel>){
        mList = newItems
        notifyDataSetChanged()
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList[position]

        holder.menuName.text = item.imageText
        // sets the image to the imageview from our itemHolder class
        Picasso.get().load(item.imageUrl).into(holder.menuImage)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("url", item.link) // Kirim URL ke WebViewActivity
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val menuImage: ImageView = itemView.findViewById(R.id.newsHoriImage)
        val menuName: TextView = itemView.findViewById(R.id.newsHoriTitle)
    }
}