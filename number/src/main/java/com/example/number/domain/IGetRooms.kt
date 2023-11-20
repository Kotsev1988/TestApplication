package com.example.number.domain

import com.example.api.api.models.Rooms
interface IGetRooms {
    suspend fun getRooms(): Rooms
}