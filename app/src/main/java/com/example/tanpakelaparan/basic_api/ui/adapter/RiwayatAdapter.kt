package com.example.tanpakelaparan.basic_api.ui.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tanpakelaparan.R
import com.example.tanpakelaparan.basic_api.data.model.RiwayatModel
import com.example.tanpakelaparan.basic_api.ui.view.WebViewActivity

class RiwayatAdapter(private val riwayatList: List<RiwayatModel>) :
    RecyclerView.Adapter<RiwayatAdapter.RiwayatViewHolder>() {

    class RiwayatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textRiwayat: TextView = itemView.findViewById(R.id.textRiwayat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiwayatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.riwayat_prediksi, parent, false)
        return RiwayatViewHolder(view)
    }

    override fun onBindViewHolder(holder: RiwayatViewHolder, position: Int) {
        val riwayat = riwayatList[position]
        holder.textRiwayat.text = if (riwayat.stunting == 1) "Terkena stunting" else "Tidak terkena stunting"
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            showRiwayatDetailsDialog(context, riwayat)
        }
    }

    override fun getItemCount(): Int {
        return riwayatList.size
    }
    private fun showRiwayatDetailsDialog(context: Context, riwayat: RiwayatModel) {
        val builder = AlertDialog.Builder(context)
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_riwayat_details, null)

        // Mengatur data ke dalam dialog
        val textJenisKelamin: TextView = dialogView.findViewById(R.id.textJenisKelamin)
        val textUmur: TextView = dialogView.findViewById(R.id.textUmur)
        val textBeratLahir: TextView = dialogView.findViewById(R.id.textBeratLahir)
        val textPanjangLahir: TextView = dialogView.findViewById(R.id.textPanjangLahir)
        val textBeratSekarang: TextView = dialogView.findViewById(R.id.textBeratSekarang)
        val textTinggiSekarang: TextView = dialogView.findViewById(R.id.textTinggiSekarang)
        val textStunting: TextView = dialogView.findViewById(R.id.textStunting)

        textJenisKelamin.text = if (riwayat.jenisKelamin==1) "Laki-laki" else "Perempuan"
        textUmur.text = "Umur: ${riwayat.umur?.toString()?: "N/A"} bulan"
        textBeratLahir.text = "Berat lahir: ${riwayat.beratLahir?.toString() ?: "N/A"} kg"
        textPanjangLahir.text = "Panjang lahir: ${riwayat.panjangLahir?.toString() ?: "N/A"} cm"
        textBeratSekarang.text = "Berat saat ini: ${riwayat.beratSekarang?.toString() ?: "N/A"} kg"
        textTinggiSekarang.text = "Tinggi saat ini: ${riwayat.tinggiSekarang?.toString() ?: "N/A"} cm"
        textStunting.text = "Stunting: ${if (riwayat.stunting == 1) "Ya" else "Tidak"}"

        builder.setView(dialogView)
            .setTitle("Riwayat Stunting Details")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }

        val dialog = builder.create()
        dialog.show()
    }


}
