package com.example.tanpakelaparan.basic_api.ui.view.info_malnutrisi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.tanpakelaparan.R

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        val btnGotoFragment2 : Button = view.findViewById(R.id.btnGotoFragment2)
        btnGotoFragment2.setOnClickListener{
            (activity as? FragmentActivity)?.replaceFragment(SecondFragment())
        }

        return view
    }

}