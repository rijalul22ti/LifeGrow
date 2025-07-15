package com.example.tanpakelaparan.basic_api.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tanpakelaparan.R
import com.example.tanpakelaparan.basic_api.data.model.ItemModel
import com.squareup.picasso.Picasso

class ItemAdapter(
    private val nlist: List<ItemModel>
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_card_view, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = nlist[position]

        Picasso.get().load(item.imageUrl).into(holder.image)
        holder.textName.text = item.description

        println("Loading item pada posisi = $position")
    }

    override fun getItemCount(): Int {
        return nlist.size
    }
    class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        val image: ImageView = itemView.findViewById(R.id.gambarRecycle)
        val textName: TextView = itemView.findViewById(R.id.description)

    }
}
