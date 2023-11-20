package com.example.hotel.data

import com.example.api.api.IHotelAPI
import com.example.api.api.models.Hotel
import com.example.hotel.domain.IGetHotel

class GetHotelImpl(
    private val api: IHotelAPI
): IGetHotel {
    override suspend fun getHotel(): Hotel = api.getHotel()
}