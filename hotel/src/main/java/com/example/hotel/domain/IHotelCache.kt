package com.example.hotel.domain

import com.example.hotel.data.room.HotelEntity

interface IHotelCache {
    suspend fun insertHotelsToDB(hotel: HotelEntity)
    suspend fun getHotelsFromCache(): HotelEntity
}