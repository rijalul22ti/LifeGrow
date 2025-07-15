package com.example.tanpakelaparan.basic_api.ui.view.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.odiproject.basic_api.utils.Resource
import com.example.tanpakelaparan.R
import com.example.tanpakelaparan.basic_api.data.model.BeritaArtikelModel
import com.example.tanpakelaparan.basic_api.data.model.ItemModel
import com.example.tanpakelaparan.basic_api.data.model.NewsHorizontalModel
import com.example.tanpakelaparan.basic_api.data.network.RetrofitInstance
import com.example.tanpakelaparan.basic_api.data.repository.NewsRepository
import com.example.tanpakelaparan.basic_api.ui.adapter.AutoSliderAdapter
import com.example.tanpakelaparan.basic_api.ui.adapter.NewsHorizontalAdapter
import com.example.tanpakelaparan.basic_api.ui.adapter.RiwayatAdapter
import com.example.tanpakelaparan.basic_api.ui.view.WebViewActivity
import com.example.tanpakelaparan.basic_api.ui.view.konten.KontenFragment
import com.example.tanpakelaparan.databinding.FragmentHomeBinding
import com.example.tanpakelaparan.basic_api.ui.viewmodel.NewsViewModel
import com.example.tanpakelaparan.basic_api.utils.ViewModelFactory
import com.example.tanpakelaparan.main.MainActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val newsViewModel: NewsViewModel by lazy {
        val repository = NewsRepository(RetrofitInstance.getNewsApi())
        ViewModelProvider(
            this, ViewModelFactory(NewsViewModel::class.java) {
                NewsViewModel(repository)
            }
        )[NewsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupAutoSlider(binding)

        setupGridMenu(binding)

//        setupApiNewsHorizontal(binding)

        setupNewsHorizontal(binding)

//        userViewModel.getUsers().observe(requireActivity()){ resp ->
//            if (resp != null) {
//                Log.d("Response API", resp.toString())
//            }else{
//                Log.d("Response API (404)", "No data found")
//            }
//        }

        return binding.root
    }

    private fun setupAutoSlider(binding: FragmentHomeBinding) {
        val images = listOf(
            BeritaArtikelModel("https://bifid.com.tr/wp-content/uploads/2023/05/Avokado-Hasat-Zamani-Bifid.webp",
                "Berita 1", "https://www.halodoc.com/kesehatan/malnutrisi"),
            BeritaArtikelModel("https://bifid.com.tr/wp-content/uploads/2023/05/Avokado-Hasat-Zamani-Bifid.webp",
                "Berita 2", "https://www.halodoc.com/kesehatan/malnutrisi"),
            BeritaArtikelModel("https://bifid.com.tr/wp-content/uploads/2023/05/Avokado-Hasat-Zamani-Bifid.webp",
                "Berita 3", "https://www.halodoc.com/kesehatan/malnutrisi"),
            BeritaArtikelModel("https://bifid.com.tr/wp-content/uploads/2023/05/Avokado-Hasat-Zamani-Bifid.webp",
                "Berita 4", "https://www.halodoc.com/kesehatan/malnutrisi"),
            BeritaArtikelModel("https://web.komdigi.go.id/resource/ZHJ1cGFsLzFfLmpwZWc=",
                "Indonesia Cegah Stunting, Antisipasi Generasi Stunting Guna Mencapai Indonesia Emas 2045", "https://www.komdigi.go.id/berita/artikel-gpr/detail/indonesia-cegah-stunting-antisipasi-generasi-stunting-guna-mencapai-indonesia-emas-2045"),
            BeritaArtikelModel("https://www.kemenkopmk.go.id/sites/default/files/articles/2024-09/WhatsApp%20Image%202024-09-25%20at%2007.12.28.jpeg",
                "Kemenko PMK Apresiasi Provinsi Bali dalam Percepatan Penurunan Stunting", "https://www.kemenkopmk.go.id/kemenko-pmk-apresiasi-provinsi-bali-dalam-percepatan-penurunan-stunting"),
        )

        binding.autoSlider.adapter = AutoSliderAdapter(images, binding.autoSlider)
        binding.dotsIndicator.attachTo(binding.autoSlider)
    }

    private fun setupGridMenu (binding: FragmentHomeBinding) {

        binding.includeHomeMenuGrid.cardMenu1.setOnClickListener{
            val intent = Intent(requireContext(), PrediksiStuntingActivity::class.java)
            startActivity(intent)
        }
        binding.includeHomeMenuGrid.cardMenu2.setOnClickListener{
            startActivity(Intent(requireContext(), RiwayatPrediksiActivity::class.java))
        }
        binding.includeHomeMenuGrid.cardMenu3.setOnClickListener{
            openKontenFragment(0)
        }
        binding.includeHomeMenuGrid.cardMenu4.setOnClickListener{
            openKontenFragment(1)
        }
        binding.includeHomeMenuGrid.cardMenu5.setOnClickListener{
            Toast.makeText(context, "Card Menu 5 Clicked", Toast.LENGTH_SHORT).show()
        }
        binding.includeHomeMenuGrid.cardMenu6.setOnClickListener{
            val intent = Intent(requireContext(), KalkulatorBMIActivity::class.java)
            startActivity(intent)
        }
    }
    private fun setupNewsHorizontal(binding: FragmentHomeBinding){
        val newsItem = listOf(
            NewsHorizontalModel(
                "Mengenal Penyebab Malnutrisi","https://res.cloudinary.com/dk0z4ums3/image/upload/v1676362653/attached_image/mengenal-penyebab-malnutrisi-pada-anak-dan-solusinya-0-alodokter.jpg"
            ),
            NewsHorizontalModel(
                "7 Makanan Bergizi","https://d1vbn70lmn1nqe.cloudfront.net/prod/wp-content/uploads/2023/02/22060206/X-Makanan-Bergizi-untuk-Cegah-Stunting-pada-Balita.jpg.webp"
            ),
            NewsHorizontalModel(
                "News C","https://images.unsplash.com/photo-1500522144261-ea64433bbe27?w=1024"
            ),
            NewsHorizontalModel(
                "News D","https://images.unsplash.com/photo-1493976040374-85c8e12f0c0e?w=1024"
            ),
            NewsHorizontalModel(
                "News E","https://images.unsplash.com/photo-1473923378535-13f8641f54a0?w=1024"
            ),
        )
        binding.newsHoriList.adapter = NewsHorizontalAdapter(newsItem)
        binding.newsHoriList.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
    }
    private fun setupApiNewsHorizontal(binding: FragmentHomeBinding){
        val adapter = NewsHorizontalAdapter(emptyList())

        newsViewModel.getNews(requireContext())
        newsViewModel.data.observe(requireActivity()){ resource ->
            when(resource){
                is Resource.Empty -> {
                    binding.loadingNewsVertical.root.visibility = View.GONE
                    binding.errorNewsVertical.root.visibility = View.GONE
                    binding.emptyNewsVertical.root.visibility = View.VISIBLE
                    binding.newsHoriList.visibility = View.GONE
                }
                is Resource.Error -> {
                    binding.loadingNewsVertical.root.visibility = View.GONE
                    binding.errorNewsVertical.root.visibility = View.VISIBLE
                    binding.emptyNewsVertical.root.visibility = View.GONE
                    binding.newsHoriList.visibility = View.GONE
                }
                is Resource.Loading -> {
                    binding.loadingNewsVertical.root.visibility = View.VISIBLE
                    binding.errorNewsVertical.root.visibility = View.GONE
                    binding.emptyNewsVertical.root.visibility = View.GONE
                    binding.newsHoriList.visibility = View.GONE
                }
                is Resource.Success -> {
                    binding.loadingNewsVertical.root.visibility = View.GONE
                    binding.errorNewsVertical.root.visibility = View.GONE
                    binding.emptyNewsVertical.root.visibility = View.GONE
                    binding.newsHoriList.visibility = View.VISIBLE

                    val menuItems = resource.data!!.articles.mapIndexed{ index, data ->
                        NewsHorizontalModel(data.title, data.urlToImage?: "https://images.unsplash.com/photo-1493976040374-85c8e12f0c0e?w=1024")
                    }
                    adapter.updateDataSet(menuItems)
                }
            }
        }

        binding.errorNewsVertical.retryButton.setOnClickListener {
            newsViewModel.getNews(requireContext(), true)
        }

        binding.newsHoriList.adapter = adapter
        binding.newsHoriList.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
    }


    private fun openKontenFragment(tabIndex: Int) {
        val fragment = KontenFragment()
        val bundle = Bundle().apply {
            putInt("TAB_INDEX", tabIndex) // Kirim posisi tab sebagai argumen
        }

        fragment.arguments = bundle

        (activity as? MainActivity)?.setActiveBottomNav(R.id.article)

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null) // Tambahkan ke back stack
            .commit()
    }
}