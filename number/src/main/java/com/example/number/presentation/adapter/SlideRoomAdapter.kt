package com.example.number.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.hotel_room.databinding.SliderRoomLayoutBinding
import com.smarteist.autoimageslider.SliderViewAdapter

class SlideRoomAdapter(private val imageUrl: List<String>): SliderViewAdapter<SlideRoomAdapter.SliderViewHolder>() {



    override fun getCount(): Int = imageUrl.size

    override fun onCreateViewHolder(parent: ViewGroup) = SliderViewHolder(
        SliderRoomLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(viewHolder: SliderViewHolder?, position: Int) {
        viewHolder?.bind(imageUrl[position])
    }

    inner class SliderViewHolder(val binding: SliderRoomLayoutBinding ) : ViewHolder(binding.root){
        fun bind(sliderList: String) {
            Glide.with(binding.sliderImageRoom.context).load(sliderList).centerCrop().into(binding.sliderImageRoom)
        }
    }
}