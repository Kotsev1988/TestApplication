package com.example.paid.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.paid_room.databinding.FragmentPaidBinding


class PaidFragment : Fragment() {

    private var _binding: FragmentPaidBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPaidBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = Bundle()
        bundle.putString("header", "Paid")
        parentFragment?.setFragmentResult("header_key", bundle)
        binding.alreadyPaid.setOnClickListener {
            val homeNavGraphResourceId = resources.getIdentifier(
                "navigation_graph",
                "id",
                requireContext().packageName
            )

            val navigationOptions = NavOptions.Builder()
                .setPopUpTo(
                    destinationId = homeNavGraphResourceId,
                    inclusive = true
                )
                .build()

            val request = NavDeepLinkRequest.Builder

                .fromUri("android-app://example.google.app/hotelFragment".toUri())
                .build()

            findNavController().navigate(request, navigationOptions)
        }
    }
}