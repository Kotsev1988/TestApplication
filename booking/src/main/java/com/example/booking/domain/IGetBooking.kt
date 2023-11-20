package com.example.booking.domain

import com.example.api.api.models.Booking
interface IGetBooking {
    suspend fun getBooking(): Booking
}