package com.example.tanpakelaparan.basic_api.ui.view.konten

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.tanpakelaparan.R
import com.example.tanpakelaparan.basic_api.ui.adapter.TabAdapter
import com.example.tanpakelaparan.databinding.FragmentKontenBinding
import com.google.android.material.tabs.TabLayoutMediator

class KontenFragment : Fragment() {

    private var _binding: FragmentKontenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentKontenBinding.inflate(inflater, container, false)

        val initialTabIndex = arguments?.getInt("TAB_INDEX", 0) ?: 0

        binding.viewPager.adapter = TabAdapter(requireActivity())

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Artikel Stunting"
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_article)
                }

                1 -> {
                    tab.text = "Makanan Sehat"
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_food)
                }
                2 -> {
                    tab.text = "Makanan Sehat"
                    tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_food)
                }

            }
        }.attach()

        binding.viewPager.post {
            binding.viewPager.setCurrentItem(initialTabIndex, false)
        }

        return binding.root
    }
}