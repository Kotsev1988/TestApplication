package com.example.number.domain

import com.example.number.data.room.RoomEntity

interface IRoomCache {
    suspend fun insertHotelRoomToDB(hotelRooms: List<RoomEntity>)
    suspend fun getHotelRoomFromCache(): List<RoomEntity>
}