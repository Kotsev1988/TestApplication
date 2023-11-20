package com.example.hotel.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.hotel.databinding.SliderLayoutBinding
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter(imageUrl: ArrayList<String>): SliderViewAdapter<SliderAdapter.SliderViewHolder>() {

    private val sliderList: ArrayList<String> = imageUrl

    override fun getCount(): Int = sliderList.size

    override fun onCreateViewHolder(parent: ViewGroup) = SliderViewHolder(
        SliderLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(viewHolder: SliderViewHolder?, position: Int) {
        viewHolder?.bind(sliderList[position])
    }

    inner class SliderViewHolder(val binding: SliderLayoutBinding ) : ViewHolder(binding.root){
        fun bind(sliderList: String) {
            Glide.with(binding.sliderImage.context).load(sliderList).centerCrop().into(binding.sliderImage)
        }
    }
}