package com.example.api.api

import com.example.api.api.models.Booking
import com.example.api.api.models.Hotel
import com.example.api.api.models.Rooms
import retrofit2.http.GET

interface IHotelAPI {
    @GET("v3/d144777c-a67f-4e35-867a-cacc3b827473")
    suspend fun getHotel(): Hotel

    @GET("v3/8b532701-709e-4194-a41c-1a903af00195")
    suspend fun getRooms(): Rooms

    @GET("v3/63866c74-d593-432c-af8e-f279d1a8d2ff")
    suspend fun getBooking(): Booking
}