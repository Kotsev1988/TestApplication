package com.example.booking.presentation.adapter.bookingAboutHotel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.booking.databinding.ItemNameOfHotelBinding
import com.example.util.adapter.DelegateAdapter
import com.example.util.adapter.DelegateAdapterItem


class BookingAboutHotelDelegateAdapter :
    DelegateAdapter<BookingAboutHotelDelegate, BookingAboutHotelDelegateAdapter.BookingAboutHotelViewHolder>(
        BookingAboutHotelDelegate::class.java
    ) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        BookingAboutHotelViewHolder(
            ItemNameOfHotelBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun bindViewHolder(
        model: BookingAboutHotelDelegate,
        viewHolder: BookingAboutHotelDelegateAdapter.BookingAboutHotelViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>,
    ) {
        viewHolder.bind(model)
    }

    inner class BookingAboutHotelViewHolder(private val binding: ItemNameOfHotelBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: BookingAboutHotelDelegate) {
            binding.ratingTextBooking.text = model.horating.toString()
            binding.ratingNameBooking.text = model.rating_name
            binding.hotelNameTextBooking.text = model.hotel_name
            binding.hotelAddressTextBooking.text = model.hotel_adress
        }
    }

}
