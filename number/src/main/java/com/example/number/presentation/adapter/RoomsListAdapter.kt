package com.example.number.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.api.api.models.Room
import com.example.hotel_room.R
import com.example.hotel_room.databinding.RoomNumberItemBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.smarteist.autoimageslider.SliderView

class RoomsListAdapter(
    private var onItemClickListener: OnItemClick,
    private val rooms: List<Room>,
) : RecyclerView.Adapter<RoomsListAdapter.RoomsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsViewHolder {
        return RoomsViewHolder(
            RoomNumberItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = rooms.size

    override fun onBindViewHolder(holder: RoomsViewHolder, position: Int) {
        holder.bind(rooms[position])
    }

    inner class RoomsViewHolder(private val binding: RoomNumberItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Room) {

            binding.checkRoom.setOnClickListener {
                onItemClickListener.onClick()
            }
            binding.numberName.text = model.name
            binding.priceForItNumberText.text = model.price_per
            binding.priceNumberText.text = model.price.toString()

            binding.sliderViewRoom.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
            binding.sliderViewRoom.setSliderAdapter(SlideRoomAdapter(model.image_urls))

            model.peculiarities.forEach {

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
                chip.text = it

                binding.options.addView(
                    chip
                )
            }
        }

    }


}