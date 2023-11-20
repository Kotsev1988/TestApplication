package com.example.hotel.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.api.api.models.AboutTheHotel
@Entity
data class HotelEntity(
    @PrimaryKey
    var id: Int?=0,
    var name: String?,
    var adress: String?,
    var minimal_price: Int?,
    var price_for_it: String?,
    var rating: Int?,
    var rating_name: String?,
    @TypeConverters(ImageUrlConverter::class)
    var image_urls: ArrayList<String>,
    @TypeConverters(AboutHotelConverter::class)
    var about_the_hotel: AboutTheHotel?
)
