package com.example.hotel.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AboutHotelEntity(
    @PrimaryKey
    var id:Int,
    var description: String,
    var peculiarities: ArrayList<String>,
)
