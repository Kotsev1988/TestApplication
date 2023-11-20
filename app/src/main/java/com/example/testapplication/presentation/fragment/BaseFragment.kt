package com.example.testapplication.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentBaseBinding
class BaseFragment : Fragment() {

    private var _binding: FragmentBaseBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentBaseBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment = (childFragmentManager.findFragmentById(R.id.mainContainerView) as NavHostFragment).navController

        childFragmentManager.setFragmentResultListener("header_key", this ){_, bundle ->
            when(bundle.getString("header")){

                "Hotel" ->{
                    binding.headerTitle.text = requireActivity().getString(com.example.hotel_room.R.string.Hotel)
                    binding.backIcon.visibility = View.INVISIBLE
                }

                "Booking" ->{
                    binding.headerTitle.text = requireActivity().getString(com.example.hotel_room.R.string.Booking)
                    binding.backIcon.visibility = View.VISIBLE
                    binding.backIcon.setOnClickListener {
                        navHostFragment.navigateUp()
                    }
                }

                "Paid" ->{
                    binding.headerTitle.text = requireActivity().getString(com.example.hotel_room.R.string.Paid)
                    binding.backIcon.visibility = View.VISIBLE
                    binding.backIcon.setOnClickListener {
                        navHostFragment.navigateUp()
                    }
                }

                "RoomNumber" ->{
                    binding.headerTitle.text = bundle.getString("hotelName")
                        binding.backIcon.visibility = View.VISIBLE

                    binding.backIcon.setOnClickListener {
                        navHostFragment.navigateUp()
                    }
                }
            }
        }
    }
}