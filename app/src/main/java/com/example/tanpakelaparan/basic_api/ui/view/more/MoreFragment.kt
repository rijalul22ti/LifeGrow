package com.example.tanpakelaparan.basic_api.ui.view.more

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tanpakelaparan.basic_api.ui.view.welcome_screen.WelcomeScreenActivity
import com.example.tanpakelaparan.databinding.FragmentMoreBinding
import com.google.firebase.auth.FirebaseAuth

class MoreFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()
        binding.btnLogout.setOnClickListener {
            logoutUser()
        }
        // Inflate the layout for this fragment
        return binding.root
    }
    private fun logoutUser() {
        // Melakukan logout dari Firebase
        auth.signOut()

        // Menampilkan toast sebagai konfirmasi logout
        Toast.makeText(this.context, "Anda berhasil logout", Toast.LENGTH_SHORT).show()

        // Arahkan kembali ke LoginActivity (sesuaikan jika perlu)
        val intent = Intent(this.context, WelcomeScreenActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

}