package com.example.booking.data

import com.example.booking.data.room.BookingDao
import com.example.booking.data.room.BookingEntity
import com.example.booking.domain.IBookingCache

class GetBookingCacheImpl (private val bookingDao: BookingDao):IBookingCache{

    override suspend fun insertBookingToDB(bookingEntity: BookingEntity) {
        bookingDao.insert(bookingEntity = bookingEntity)
    }

    override suspend fun getBookingFromCache(): BookingEntity {
        return bookingDao.getAllBooking()
    }
}