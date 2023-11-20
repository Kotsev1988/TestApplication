package com.example.booking.presentation.adapter.bookingDeparture

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.booking.databinding.ItemAboutBokingBinding
import com.example.util.adapter.DelegateAdapter
import com.example.util.adapter.DelegateAdapterItem

class BookingDepartureDelegateAdapter:
    DelegateAdapter<BookingDepartureDelegate, BookingDepartureDelegateAdapter.BookingDepartureViewHolder>(
        BookingDepartureDelegate::class.java
    ) {


    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = BookingDepartureViewHolder(
        ItemAboutBokingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun bindViewHolder(
        model: BookingDepartureDelegate,
        viewHolder: BookingDepartureViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>,
    ) {
        viewHolder.bind(model)
    }

    inner class BookingDepartureViewHolder(private val binding: ItemAboutBokingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: BookingDepartureDelegate) {

            binding.departureText.text = model.departure
            binding.arrivalCountryText.text = model.arrival_country
            binding.datesText.text = model.tour_date_start+" - "+model.tour_date_stop
            binding.numberOfNightsText.text = model.number_of_nights.toString()
            binding.hotelText.text = model.hotel_name
            binding.roomText.text = model.room
            binding.nutritionText.text = model.nutrition

        }
    }


}