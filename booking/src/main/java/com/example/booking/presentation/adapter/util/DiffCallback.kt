package com.example.booking.presentation.adapter.util

import androidx.recyclerview.widget.DiffUtil
import com.example.booking.domain.model.TouristInfo

class DiffCallback : DiffUtil.ItemCallback<TouristInfo>() {
    override fun areItemsTheSame(oldItem: TouristInfo, newItem: TouristInfo): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: TouristInfo, newItem: TouristInfo): Boolean {
        return oldItem == newItem
    }
    override fun getChangePayload(oldItem: TouristInfo, newItem: TouristInfo): Any? {

        if (oldItem.checkFeilds != newItem.checkFeilds) {
            return ChangePayloads.IsAllFilled
        }
        return super.getChangePayload(oldItem, newItem)
    }
}
enum class ChangePayloads{
    IsAllFilled
}