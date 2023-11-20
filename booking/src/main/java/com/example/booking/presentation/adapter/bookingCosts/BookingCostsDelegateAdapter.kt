package com.example.booking.presentation.adapter.bookingCosts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.booking.databinding.ItemTotalPriceBookingBinding
import com.example.util.adapter.DelegateAdapter
import com.example.util.adapter.DelegateAdapterItem

class BookingCostsDelegateAdapter:
    DelegateAdapter<BookingCostsDelegate, BookingCostsDelegateAdapter.BookingCostsViewHolder>(
        BookingCostsDelegate::class.java
    ) {
    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = BookingCostsViewHolder(
        ItemTotalPriceBookingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun bindViewHolder(
        model: BookingCostsDelegate,
        viewHolder: BookingCostsDelegateAdapter.BookingCostsViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>,
    ) {
        viewHolder.bind(model)
    }

    inner class BookingCostsViewHolder(private val binding: ItemTotalPriceBookingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: BookingCostsDelegate) {

            binding.torText.text = model.tour_price.toString()+" Р"
            binding.fuelChargeText.text = model.fuel_charge.toString()+" Р"
            binding.serviceChargeText.text = model.service_charge.toString() +" Р"
            val totalCount = (model.tour_price + model.fuel_charge + model.service_charge)
            binding.totalCountText.text = totalCount.toString() + " Р"
        }
    }
}