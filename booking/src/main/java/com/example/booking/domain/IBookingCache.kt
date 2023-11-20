package com.example.booking.domain

import com.example.booking.data.room.BookingEntity

interface IBookingCache {
    suspend fun insertBookingToDB(bookingEntity: BookingEntity)
    suspend fun getBookingFromCache(): BookingEntity
}