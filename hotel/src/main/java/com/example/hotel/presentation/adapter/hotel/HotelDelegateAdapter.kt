package com.example.hotel.presentation.adapter.hotel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hotel.databinding.ItemHotelBinding
import com.example.util.adapter.DelegateAdapter
import com.example.util.adapter.DelegateAdapterItem
import com.example.hotel.presentation.adapter.SliderAdapter
import com.smarteist.autoimageslider.SliderView

class HotelDelegateAdapter:
    DelegateAdapter<HotelDelegate, HotelDelegateAdapter.HotelsViewHolder>(
        HotelDelegate::class.java
    ){

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = HotelsViewHolder(
        ItemHotelBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun bindViewHolder(
        model: HotelDelegate,
        viewHolder: HotelsViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>,
    ) {
        viewHolder.bind(model)
    }

    inner class HotelsViewHolder(private val binding: ItemHotelBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(model: HotelDelegate) {
            binding.hotelNameText.text = model.hotels.name
            binding.hotelAddressText.text = model.hotels.adress
            binding.priceForItText.text = model.hotels.price_for_it
            binding.priceText.text = "от "+ model.hotels.minimal_price.toString()
            binding.rating.text = model.hotels.rating.toString()
            binding.ratingName.text = model.hotels.rating_name

            binding.slider.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
            binding.slider.setSliderAdapter(SliderAdapter(model.hotels.image_urls))
        }
    }
}