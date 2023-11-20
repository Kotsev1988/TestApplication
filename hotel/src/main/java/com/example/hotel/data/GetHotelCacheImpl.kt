package com.example.hotel.data

import com.example.hotel.data.room.HotelDao
import com.example.hotel.data.room.HotelEntity
import com.example.hotel.domain.IHotelCache

class GetHotelCacheImpl(private val hotelDao: HotelDao,):IHotelCache{

    override suspend fun insertHotelsToDB(hotel: HotelEntity) {
        hotelDao.insert(hotel = hotel)
    }

    override suspend fun getHotelsFromCache():HotelEntity{
        return hotelDao.getAllHotels()
    }
}