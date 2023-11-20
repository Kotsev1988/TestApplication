package com.example.number.data

import com.example.api.api.IHotelAPI
import com.example.api.api.models.Rooms
import com.example.number.domain.IGetRooms

class GetRoomsImpl(
    private val api: IHotelAPI
): IGetRooms {
    override suspend fun getRooms(): Rooms = api.getRooms()
}