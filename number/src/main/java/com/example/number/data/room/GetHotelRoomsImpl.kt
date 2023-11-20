package com.example.number.data.room

import com.example.number.domain.IRoomCache

class GetHotelRoomsImpl (private val roomsDao: RoomDao) : IRoomCache {

    override suspend fun insertHotelRoomToDB(hotelRooms: List<RoomEntity>) {
        roomsDao.insert(hotelRooms)
    }

    override suspend fun getHotelRoomFromCache(): List<RoomEntity> {
        return roomsDao.getAllHotels()
    }
}