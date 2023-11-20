package com.example.number.presentation.fragment

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
import com.example.hotel_room.databinding.FragmentRoomsBinding
import com.example.number.di.RoomsComponentViewModel
import com.example.number.presentation.adapter.OnItemClick
import com.example.number.presentation.adapter.RoomsListAdapter
import javax.inject.Inject

const val KAY_PARENT = "header_key"
class RoomsFragment : Fragment() {

    private var _binding: FragmentRoomsBinding? = null
    private val binding get() = _binding!!

    @Inject
    internal lateinit var viewModelFactory: dagger.Lazy<RoomsViewModel.Factory>

    private val viewModel: RoomsViewModel by viewModels {
        viewModelFactory.get()
    }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<RoomsComponentViewModel>()
            .newRoomsComponent.inject(this)
        super.onAttach(context)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {


        val hotelName = arguments?.getString("metadataFileSyncFilter")

        val bundle = Bundle()
        bundle.putString("header", "RoomNumber")
        bundle.putString("hotelName", hotelName)
        parentFragment?.setFragmentResult(KAY_PARENT,bundle)

        _binding = FragmentRoomsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.frameLoadRooms.visibility = View.VISIBLE
        viewModel.listOfRooms.observe(viewLifecycleOwner) {
            renderData(it)
        }
        viewModel.init()
    }

    private fun renderData(it: RoomsAppState) {
        when (it) {
            is RoomsAppState.OnSuccess -> {
                binding.recyclerViewRooms.adapter = RoomsListAdapter(object :
                    OnItemClick {
                    override fun onClick() {
                        val request = NavDeepLinkRequest.Builder
                            .fromUri("android-app://example.google.app/bookingFragment".toUri())
                            .build()

                        findNavController().navigate(request)
                    }
                }, it.rooms)
                binding.frameLoadRooms.visibility = View.GONE
            }

            is RoomsAppState.Error -> {
                Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
            }

            is RoomsAppState.Loading -> {
                binding.frameLoadRooms.visibility = View.VISIBLE
            }
        }
    }
}