package com.example.hotel.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.example.hotel.databinding.FragmentHotelBinding
import com.example.hotel.di.ArticleComponentViewModel
import com.example.util.adapter.MainAdapter
import com.example.hotel.presentation.adapter.adoutHotel.AboutHotelDelegate
import com.example.hotel.presentation.adapter.adoutHotel.AboutHotelDelegateAdapter
import com.example.hotel.presentation.adapter.hotel.HotelDelegate
import com.example.hotel.presentation.adapter.hotel.HotelDelegateAdapter
import javax.inject.Inject

const val KAY_PARENT = "header_key"

class HotelFragment : Fragment() {

    private var _binding: FragmentHotelBinding? = null
    private val binding get() = _binding!!

    private val mainAdapter by lazy {
        MainAdapter.Builder()
            .add(HotelDelegateAdapter())
            .add(AboutHotelDelegateAdapter())
            .build()
    }

    @Inject
    internal lateinit var viewModelFactory: dagger.Lazy<HotelViewModel.Factory>

    private val viewModel: HotelViewModel by viewModels {
        viewModelFactory.get()
    }

    private var hotelName: String = ""

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<ArticleComponentViewModel>()
            .newHotelComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentHotelBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val bundle = Bundle()
        bundle.putString("header", "Hotel")
        parentFragment?.setFragmentResult(KAY_PARENT, bundle)

        binding.frameLoadHotels.visibility = View.VISIBLE
        binding.checkRoomForThisHotel.isClickable = false
        viewModel.init()

        viewModel.hotelItem.observe(viewLifecycleOwner) {
            renderData(it)
        }

        viewModel.clickEvent.observe(viewLifecycleOwner) {
            val name = it.goToMenuDetailsIfNotHandled()
            val bundle = Bundle()
            if (name != null) {
                bundle.putString("Hotel_Name", name)
                val request = NavDeepLinkRequest.Builder
                    .fromUri("android-app://example.google.app/roomsFragment/${name}".toUri())
                    .build()

                findNavController().navigate(request)
            }
        }

        binding.checkRoomForThisHotel.setOnClickListener {
            viewModel.navigateToRooms(hotelName)
        }
    }

    private fun renderData(it: HotelAppState) {

        when (it) {
            is HotelAppState.OnSuccess -> {
                hotelName = it.hotel.name.toString()
                val listDelegate = listOf(
                    HotelDelegate(it.hotel),
                    it.hotel.about_the_hotel?.let { it1 -> AboutHotelDelegate(it1) }
                )

                mainAdapter.submitList(listDelegate)
                binding.recyclerHotels.adapter = mainAdapter
                binding.frameLoadHotels.visibility = View.GONE
                binding.checkRoomForThisHotel.isClickable = true
            }

            is HotelAppState.Error -> {
                Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
            }

            is HotelAppState.Loading -> {
                binding.frameLoadHotels.visibility = View.VISIBLE
            }
        }
    }
}