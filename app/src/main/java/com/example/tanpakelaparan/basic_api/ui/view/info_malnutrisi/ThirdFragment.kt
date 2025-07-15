package com.example.tanpakelaparan.basic_api.ui.view.info_malnutrisi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.tanpakelaparan.R

class ThirdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_third, container, false)

        val link = arguments?.getString("link")

        val textView: TextView = view.findViewById(R.id.textView)

        if (link != null) {
            textView.text = "Sumber : " + link
        }

        return view
    }

}