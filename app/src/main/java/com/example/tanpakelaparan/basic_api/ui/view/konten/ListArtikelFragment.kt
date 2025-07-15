package com.example.tanpakelaparan.basic_api.ui.view.konten

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.odiproject.basic_api.utils.Resource
import com.example.tanpakelaparan.basic_api.data.model.BeritaArtikelModel
import com.example.tanpakelaparan.basic_api.data.model.MakananPostRequest
import com.example.tanpakelaparan.basic_api.data.model.NewsHorizontalModel
import com.example.tanpakelaparan.basic_api.data.network.RetrofitInstance
import com.example.tanpakelaparan.basic_api.data.repository.MakananRepository
import com.example.tanpakelaparan.basic_api.ui.adapter.ArtikelAdapter
import com.example.tanpakelaparan.basic_api.ui.adapter.NewsHorizontalAdapter
import com.example.tanpakelaparan.basic_api.ui.viewmodel.MakananViewModel
import com.example.tanpakelaparan.basic_api.utils.ViewModelFactory
import com.example.tanpakelaparan.databinding.FragmentHomeBinding
import com.example.tanpakelaparan.databinding.FragmentListArtikelBinding
import com.google.android.material.snackbar.Snackbar

class ListArtikelFragment : Fragment() {

    private var _binding: FragmentListArtikelBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter : NewsHorizontalAdapter

    private val makananViewModel: MakananViewModel by activityViewModels {
        ViewModelFactory(MakananViewModel::class.java) {
            val repository = MakananRepository(RetrofitInstance.getCrudApi())
            MakananViewModel(repository)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListArtikelBinding.inflate(inflater, container, false)

        adapter = NewsHorizontalAdapter(emptyList())
        binding.productList.adapter = adapter
        binding.productList.layoutManager = LinearLayoutManager(this.context)

//        getMakanan()
//        createMakanan()
        setupArtikel(binding)

        return binding.root
    }
    private fun setupArtikel(binding: FragmentListArtikelBinding){
        val newsItem = listOf(
            BeritaArtikelModel(
                "https://d1vbn70lmn1nqe.cloudfront.net/prod/wp-content/uploads/2022/10/04042156/Malnutrisi.jpg.webp",
                "Mengenal Penyebab Malnutrisi",
                "https://www.halodoc.com/kesehatan/malnutrisi?srsltid=AfmBOoqgpwEIkAA-Hu-vzmnU7M5csCjl8S0PomlHoNbTXKnlQB-IX3ko#google_vignette"
            ),
            BeritaArtikelModel(
                "https://yankes.kemkes.go.id/img/bg-img/gambarartikel_1661498786_242330.jpg",
                "Mengenal Apa Itu Stunting…",
                "https://yankes.kemkes.go.id/view_artikel/1388/mengenal-apa-itu-stunting"
            ),
            BeritaArtikelModel(
                "https://ayosehat.kemkes.go.id/imagex/content/169105177264cb66fc9afd95.94181442.webp",
                "Stunting",
                "https://ayosehat.kemkes.go.id/topik-penyakit/defisiensi-nutrisi/stunting"
            ),

        )
        binding.productList.adapter = ArtikelAdapter(newsItem)
        binding.productList.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
    }


    private fun getMakanan(){
        makananViewModel.getMakanan(requireContext())
        makananViewModel.data.observe(requireActivity()) { resource ->
            when (resource) {
                is Resource.Empty -> {
                    Log.d("EMPTY DATA", "Data Tidak Tersedia")
                    binding.loadingProduct.root.visibility = View.GONE
                    binding.emptyProduct.root.visibility = View.VISIBLE
                    binding.errorProduct.root.visibility = View.GONE
                    binding.productList.visibility = View.GONE
                }

                is Resource.Error -> {
                    binding.loadingProduct.root.visibility = View.GONE
                    binding.emptyProduct.root.visibility = View.GONE
                    binding.errorProduct.root.visibility = View.VISIBLE
                    binding.productList.visibility = View.GONE
                    binding.errorProduct.errorMessage.text = resource.message
                }

                is Resource.Loading -> {
                    binding.loadingProduct.root.visibility = View.VISIBLE
                    binding.emptyProduct.root.visibility = View.GONE
                    binding.errorProduct.root.visibility = View.GONE
                    binding.productList.visibility = View.GONE
                }

                is Resource.Success -> {
                    binding.loadingProduct.root.visibility = View.GONE
                    binding.emptyProduct.root.visibility = View.GONE
                    binding.errorProduct.root.visibility = View.GONE
                    binding.productList.visibility = View.VISIBLE

                    val menuItems = resource.data!!.items.mapIndexed { index, data ->
                        NewsHorizontalModel(
                            data.name,
                            data.imageUrl
                        )
                    }
                    adapter.updateDataSet(menuItems)
                }

                else -> {}
            }
        }
    }

    private fun createMakanan() {
        val name = "Keju"
        val desc = "Berbahan dasar susu"
        val linkGambar = "https://awsimages.detik.net.id/community/media/visual/2021/01/30/keju.jpeg?w=700&q=90"
        val price = 5000
        val nutrisi = "Untuk 100g = Kalori (kcal) 402\t\n" +
                "Jumlah Lemak 33 g\t\n" +
                "Kolesterol 105 mg\t\n" +
                "Natrium 621 mg\t\n" +
                "Kalium 98 mg"

        val products = listOf(
            MakananPostRequest(
                name = name,
                description = desc,
                price = price,
                imageUrl = linkGambar,
                nutrition = nutrisi,
            )
        )
        makananViewModel.createMakanan(requireContext(), products)
        makananViewModel.createStatus.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    // Show a loading indicator for create operation
                }

                is Resource.Success -> {

                    Snackbar.make(
                        binding.root,
                        "Data makanan created successfully!",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }

                is Resource.Error -> {
                    Snackbar.make(
                        binding.root,
                        resource.message ?: "Failed to create data makanan.",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                is Resource.Empty -> TODO()
                else -> {}
            }
        }
    }
}