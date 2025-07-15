package com.example.tanpakelaparan.basic_api.ui.view.info_malnutrisi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.tanpakelaparan.R

class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        val btnGotoFragment3 : Button = view.findViewById(R.id.btnGotoFragment3)
        btnGotoFragment3.setOnClickListener{
            val thirdFragment = ThirdFragment()
            val bundle = Bundle()
            bundle.putString("link", "https://www.halodoc.com/kesehatan/malnutrisi")
            thirdFragment.arguments = bundle

            (activity as? FragmentActivity)?.replaceFragment(thirdFragment)
        }

        return view
    }

}