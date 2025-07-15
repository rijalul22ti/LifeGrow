package com.example.tanpakelaparan.basic_api.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tanpakelaparan.basic_api.ui.view.konten.ListArtikelFragment

class TabAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    private val fragmentList = listOf(
        ListArtikelFragment(),
        ListArtikelFragment(),
        ListArtikelFragment()
    )

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}