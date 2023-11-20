package com.example.hotel.domain

import com.example.api.api.models.Hotel
interface IGetHotel {
    suspend fun getHotel(): Hotel
}