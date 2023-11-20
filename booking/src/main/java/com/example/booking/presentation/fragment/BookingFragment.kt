package com.example.booking.presentation.fragment

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
import com.example.booking.databinding.FragmentBookingBinding
import com.example.booking.di.BookingComponentViewModel
import com.example.booking.domain.model.CheckFilled
import com.example.booking.domain.model.TouristInfo
import com.example.booking.presentation.adapter.util.OnAddItemClickListener
import com.example.booking.presentation.adapter.bookingAboutBuyer.BookingAboutBuyerDelegate
import com.example.booking.presentation.adapter.bookingAboutBuyer.BookingAboutBuyerDelegateAdapter
import com.example.booking.presentation.adapter.bookingAboutHotel.BookingAboutHotelDelegate
import com.example.booking.presentation.adapter.bookingAboutHotel.BookingAboutHotelDelegateAdapter
import com.example.booking.presentation.adapter.bookingCosts.BookingCostsDelegate
import com.example.booking.presentation.adapter.bookingCosts.BookingCostsDelegateAdapter
import com.example.booking.presentation.adapter.bookingDeparture.BookingDepartureDelegate
import com.example.booking.presentation.adapter.bookingDeparture.BookingDepartureDelegateAdapter
import com.example.booking.presentation.adapter.bookingTourists.BookingTouristsDelegate
import com.example.booking.presentation.adapter.bookingTourists.BookingTouristsDelegateAdapter
import com.example.util.adapter.MainAdapter
import javax.inject.Inject

const val KAY_PARENT = "header_key"

class BookingFragment : Fragment() {

    private var _binding: FragmentBookingBinding? = null
    private val binding get() = _binding!!

    @Inject
    internal lateinit var viewModelFactory: dagger.Lazy<BookingViewModel.Factory>

    private val viewModel: BookingViewModel by viewModels {
        viewModelFactory.get()
    }

    private val mainAdapter by lazy {
        MainAdapter.Builder()
            .add(BookingAboutHotelDelegateAdapter())
            .add(BookingDepartureDelegateAdapter())
            .add(
                BookingAboutBuyerDelegateAdapter (
                    {
                        viewModel.setPhone(it)
                    },
                    {
                        viewModel.setEmail(it)
                    }
                )
            )
            .add(BookingTouristsDelegateAdapter(
                object : OnAddItemClickListener {
                    override fun onAddClick() {
                        viewModel.addTourist()
                    }
                },
                {
                    viewModel.onCheckedFields(it)
                },
                {isCollapsed, pos ->
                    viewModel.setCollapsed(isCollapsed, pos)
                },

                { firstName, pos ->
                    viewModel.setFirstName(firstName, pos)
                },

                { lastName, pos ->
                    viewModel.setLastName(lastName, pos)
                },

                { birthday, pos ->
                    viewModel.setBirthday(birthday, pos)
                },

                { citizenship, pos ->
                    viewModel.setCitizenship(citizenship, pos)
                },

                { passportNumber, pos ->
                    viewModel.setPassportNumber(passportNumber, pos)
                },
                { validate, pos ->
                    viewModel.setValidate(validate, pos)
                }
            ))
            .add(BookingCostsDelegateAdapter())
            .build()
    }


    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<BookingComponentViewModel>()
            .newBookingComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val bundle = Bundle()
        bundle.putString("header", "Booking")
        parentFragment?.setFragmentResult(KAY_PARENT, bundle)

        _binding = FragmentBookingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.frameLoadBooking.visibility = View.VISIBLE
        viewModel.bookingItem.observe(viewLifecycleOwner) {
            renderData(it)
        }
        viewModel.init()

        binding.payForBookingRoom.setOnClickListener {
            viewModel.checkFields()
        }

        viewModel.clickEvent.observe(viewLifecycleOwner){
            if (it.goToMenuDetailsIfNotHandled() == true) {
                val request = NavDeepLinkRequest.Builder
                    .fromUri("android-app://example.google.app/paidFragment".toUri())
                    .build()

                findNavController().navigate(request)
            }
        }
    }

    private fun renderData(it: BookingAppState) {
        when (it) {
            is BookingAppState.OnSuccess -> {
                val touristsUpdate: ArrayList<TouristInfo> = arrayListOf()
                touristsUpdate.addAll(it.tourist)

                val check = CheckFilled(false)

                val listDelegate = listOf(
                    BookingAboutHotelDelegate(
                        it.booking.horating,
                        it.booking.rating_name,
                        it.booking.hotel_adress,
                        it.booking.hotel_name
                    ),
                    BookingDepartureDelegate(
                        it.booking.hotel_name,
                        it.booking.departure,
                        it.booking.arrival_country,
                        it.booking.tour_date_start,
                        it.booking.tour_date_stop,
                        it.booking.number_of_nights,
                        it.booking.room,
                        it.booking.nutrition
                    ),

                    BookingAboutBuyerDelegate(
                        it.contacts, false
                    ),

                    BookingTouristsDelegate(
                        touristsUpdate, check
                    ),

                    BookingCostsDelegate(
                        it.booking.tour_price,
                        it.booking.fuel_charge,
                        it.booking.service_charge
                    )
                )
                mainAdapter.submitList(listDelegate)
                binding.recyclerBooking.adapter = mainAdapter
                binding.frameLoadBooking.visibility = View.GONE
            }

            is BookingAppState.OnAddTourist -> {

                val touristsUpdate: ArrayList<TouristInfo> = arrayListOf()
                touristsUpdate.addAll(it.tourist)

                val check = CheckFilled(false)

                val listDelegate = listOf(
                    BookingAboutHotelDelegate(
                        it.booking.horating,
                        it.booking.rating_name,
                        it.booking.hotel_adress,
                        it.booking.hotel_name
                    ),
                    BookingDepartureDelegate(
                        it.booking.hotel_name,
                        it.booking.departure,
                        it.booking.arrival_country,
                        it.booking.tour_date_start,
                        it.booking.tour_date_stop,
                        it.booking.number_of_nights,
                        it.booking.room,
                        it.booking.nutrition
                    ),

                    BookingAboutBuyerDelegate(
                        it.contacts, false
                    ),

                    BookingTouristsDelegate(
                        touristsUpdate, check
                    ),


                    BookingCostsDelegate(
                        it.booking.tour_price,
                        it.booking.fuel_charge,
                        it.booking.service_charge
                    )
                )

                mainAdapter.submitList(listDelegate)
            }

            is BookingAppState.CheckFields ->{

                val tourists1: ArrayList<TouristInfo> = arrayListOf()

                it.tourist.forEach {
                    tourists1.add(TouristInfo(
                        it.id, first_name=it.first_name, last_name=it.last_name,
                        birth_date=it.birth_date, citizenship=it.citizenship, document_number=it.document_number, validate=it.validate,
                        checkFeilds=true, collapsed = it.collapsed))
                }

                val check = CheckFilled(true)
                val listDelegate = listOf(
                    BookingAboutHotelDelegate(
                        it.booking.horating,
                        it.booking.rating_name,
                        it.booking.hotel_adress,
                        it.booking.hotel_name
                    ),
                    BookingDepartureDelegate(
                        it.booking.hotel_name,
                        it.booking.departure,
                        it.booking.arrival_country,
                        it.booking.tour_date_start,
                        it.booking.tour_date_stop,
                        it.booking.number_of_nights,
                        it.booking.room,
                        it.booking.nutrition
                    ),

                    BookingAboutBuyerDelegate(
                        it.contacts, true
                    ),

                    BookingTouristsDelegate(
                        tourists1, check
                    ),

                    BookingCostsDelegate(
                        it.booking.tour_price,
                        it.booking.fuel_charge,
                        it.booking.service_charge
                    )
                )
                mainAdapter.submitList(listDelegate)
            }

            is BookingAppState.CheckedFields ->{

                val touristsUpdate: ArrayList<TouristInfo> = arrayListOf()
                touristsUpdate.addAll(it.tourist)
                val check =  CheckFilled(false)

                val listDelegate = listOf(
                    BookingAboutHotelDelegate(
                        it.booking.horating,
                        it.booking.rating_name,
                        it.booking.hotel_adress,
                        it.booking.hotel_name
                    ),
                    BookingDepartureDelegate(
                        it.booking.hotel_name,
                        it.booking.departure,
                        it.booking.arrival_country,
                        it.booking.tour_date_start,
                        it.booking.tour_date_stop,
                        it.booking.number_of_nights,
                        it.booking.room,
                        it.booking.nutrition
                    ),

                    BookingAboutBuyerDelegate(
                        it.contacts, false
                    ),

                    BookingTouristsDelegate(
                        touristsUpdate, check
                    ),

                    BookingCostsDelegate(
                        it.booking.tour_price,
                        it.booking.fuel_charge,
                        it.booking.service_charge
                    )
                )

                mainAdapter.submitList(listDelegate)
            }

            is BookingAppState.Navigate -> {
                val request = NavDeepLinkRequest.Builder
                    .fromUri("android-app://example.google.app/paidFragment".toUri())
                    .build()

                findNavController().navigate(request)
            }

            is BookingAppState.NeedAddTourist ->{
                Toast.makeText(requireContext(), "Добавьте хотя бы одного туриста", Toast.LENGTH_SHORT).show()
            }

            is BookingAppState.Error -> {
                Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
            }

            is BookingAppState.Loading -> {
                binding.frameLoadBooking.visibility = View.VISIBLE
            }
        }
    }
}