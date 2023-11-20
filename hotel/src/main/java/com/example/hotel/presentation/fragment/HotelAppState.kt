package com.example.hotel.presentation.fragment

import com.example.api.api.models.Hotel

sealed class HotelAppState{
    data class OnSuccess(
        val hotel: Hotel
    ): HotelAppState()

    data class Error(val error: String) : HotelAppState()
    object Loading : HotelAppState()
}
