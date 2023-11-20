package com.example.hotel.presentation.adapter.adoutHotel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.hotel.R
import com.example.hotel.databinding.ItemAboutHotelBinding
import com.example.util.adapter.DelegateAdapter
import com.example.util.adapter.DelegateAdapterItem
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
class AboutHotelDelegateAdapter:
    DelegateAdapter<AboutHotelDelegate, AboutHotelDelegateAdapter.AboutHotelsViewHolder>(
        AboutHotelDelegate::class.java
    ) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = AboutHotelsViewHolder(
        ItemAboutHotelBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun bindViewHolder(
        model: AboutHotelDelegate,
        viewHolder: AboutHotelsViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>,
    ) {
        viewHolder.bind(model)
    }

    inner class AboutHotelsViewHolder(private val binding: ItemAboutHotelBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: AboutHotelDelegate) {

            binding.hotelDescriptionText.text = model.about_the_hotel.description
            model.about_the_hotel.peculiarities.forEach {peculiarities ->

                val chipDrawable = ChipDrawable.createFromAttributes(
                    itemView.context,
                    null,
                    0,
                    R.style.chipStyle
                )

                val chip = Chip(itemView.context)
                chip.setChipDrawable(chipDrawable)
                chip.setTextColor(ContextCompat.getColor(itemView.context, R.color.chip_text_color))
                chip.setTextAppearance(R.style.ChipTextAppearance)
                chip.text = peculiarities

                    binding.chips.addView(
                        chip
                    )
            }
        }
    }
}