package com.example.booking.data

import com.example.api.api.IHotelAPI
import com.example.api.api.models.Booking
import com.example.booking.domain.IGetBooking

class GetBookingImpl(
    private val api: IHotelAPI
): IGetBooking {
    override suspend fun getBooking(): Booking = api.getBooking()
}