package com.example.tanpakelaparan.basic_api.ui.view.welcome_screen

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter (
    activity: WelcomeScreenActivity,
    private val fragments: List<Fragment>
): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}